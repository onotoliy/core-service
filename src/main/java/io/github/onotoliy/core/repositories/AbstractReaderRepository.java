package io.github.onotoliy.core.repositories;

import io.github.onotoliy.core.data.Option;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.data.page.Meta;
import io.github.onotoliy.core.data.page.Page;
import io.github.onotoliy.core.data.page.Paging;
import io.github.onotoliy.core.exceptions.NotFoundException;
import io.github.onotoliy.core.rpc.KeycloakRPC;
import io.github.onotoliy.core.utils.GUIDs;
import io.github.onotoliy.core.utils.Strings;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.OrderField;
import org.jooq.Record;
import org.jooq.SelectJoinStep;
import org.jooq.Table;
import org.jooq.TableField;

/**
 * Базовый репозиторий чтения записей из БД.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @param <R> Запись из БД
 * @param <T> Таблица в БД
 * @author Anatoliy Pokhresnyi
 */
public abstract class AbstractReaderRepository<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter,
    R extends Record,
    T extends Table<R>>
implements ReaderRepository<E, P> {

    /**
     * Таблица.
     */
    protected final T table;

    /**
     * Уникальный идентификатор.
     */
    protected final TableField<R, UUID> uuid;

    /**
     * Название.
     */
    protected final TableField<R, String> name;

    /**
     * Автор.
     */
    protected final TableField<R, UUID> author;

    /**
     * Дата создания.
     */
    protected final TableField<R, LocalDateTime> creationDate;

    /**
     * Дата удаления.
     */
    protected final TableField<R, LocalDateTime> deletionDate;

    /**
     * Контекст подключения к БД.
     */
    protected final DSLContext dsl;

    /**
     * Сервис чтения пользователей.
     */
    protected final KeycloakRPC user;

    /**
     * Конструктор.
     *
     * @param table Таблица.
     * @param uuid Уникальный идентификатор.
     * @param name Название.
     * @param author Автор.
     * @param creationDate Дата создания.
     * @param deletionDate Дата удаления.
     * @param dsl Контекст подключения к БД.
     * @param user Сервис чтения пользователей.
     */
    protected AbstractReaderRepository(
            final T table,
            final TableField<R, UUID> uuid,
            final TableField<R, String> name,
            final TableField<R, UUID> author,
            final TableField<R, LocalDateTime> creationDate,
            final TableField<R, LocalDateTime> deletionDate,
            final DSLContext dsl,
            final KeycloakRPC user) {
        this.table = table;
        this.uuid = uuid;
        this.name = name;
        this.author = author;
        this.creationDate = creationDate;
        this.deletionDate = deletionDate;
        this.dsl = dsl;
        this.user = user;
    }

    /**
     * Преобзазование записи из БД в объект.
     *
     * @param record Запись из БД.
     * @return Объект.
     */
    protected abstract E toDTO(Record record);

    /**
     * Получение select запроса из таблицы.
     *
     * @return Запрос.
     */
    protected SelectJoinStep<Record> findQuery() {
        return dsl.select().from(table);
    }

    @Override
    public Optional<E> getOptional(final UUID uuid) {
        return findQuery().where(this.uuid.eq(uuid)).fetchOptional(this::toDTO);
    }

    @Override
    public E get(final UUID uuid) {
        return getOptional(uuid).orElseThrow(
            () -> new NotFoundException(table, uuid));
    }

    @Override
    public List<Option> getAll() {
        return findQuery().where(deletionDate.isNull())
                          .fetch(record ->
                              new Option(GUIDs.format(record, uuid),
                                  Strings.format(record, name)));
    }

    @Override
    public Page<E> getAll(final P parameter) {
        return new Page<>(
            new Meta(
                dsl.selectCount()
                   .from(table)
                   .where(where(parameter))
                   .fetchOptional(0, int.class)
                   .orElse(0),
                new Paging(parameter.getOffset(), parameter.getNumberOfRows())),
            findQuery().where(where(parameter))
                       .orderBy(orderBy())
                       .offset(parameter.getOffset())
                       .limit(parameter.getNumberOfRows())
                       .fetch(this::toDTO));
    }

    /**
     * Получение колонок по которым будет производиться сортировка.
     *
     * @return Колонки по которым будет производиться сортировка.
     */
    protected List<? extends OrderField<?>> orderBy() {
        return new LinkedList<>(Collections.singleton(creationDate.desc()));
    }

    /**
     * Получение условий выборки данных из БД.
     *
     * @param parameter Поисковы параметры.
     * @return Условия выборки данных из БД.
     */
    protected List<Condition> where(final P parameter) {
        return new LinkedList<>(Collections.singleton(deletionDate.isNull()));
    }

    /**
     * Преобразование пользователя из уникального идентификатора в объект.
     *
     * @param record Запись из БД.
     * @param field Колонка содержащая уникальный идентификатор пользователя.
     * @return Пользователь.
     */
    protected Option formatUser(final Record record, final Field<UUID> field) {
        return user.find(record.getValue(field, UUID.class));
    }
}
