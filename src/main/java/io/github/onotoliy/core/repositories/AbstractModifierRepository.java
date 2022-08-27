package io.github.onotoliy.core.repositories;

import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.exceptions.NotFoundException;
import io.github.onotoliy.core.exceptions.NotUniqueException;
import io.github.onotoliy.core.rpc.KeycloakRPC;
import io.github.onotoliy.core.utils.Dates;
import io.github.onotoliy.core.utils.GUIDs;
import io.github.onotoliy.core.utils.Strings;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UpdateConditionStep;
import org.jooq.UpdateSetMoreStep;
import org.jooq.impl.DSL;

import static io.github.onotoliy.core.utils.Dates.toJavaLocalDatetime;

/**
 * Базовый репозиторий управления записями из БД.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @param <R> Запись из БД
 * @param <T> Таблица в БД
 * @author Anatoliy Pokhresnyi
 */
public abstract class AbstractModifierRepository<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter,
    R extends Record,
    T extends Table<R>>
extends AbstractReaderRepository<E, P, R, T>
implements ModifierRepository<E, P> {

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
    protected AbstractModifierRepository(
            final T table,
            final TableField<R, UUID> uuid,
            final TableField<R, String> name,
            final TableField<R, UUID> author,
            final TableField<R, LocalDateTime> creationDate,
            final TableField<R, LocalDateTime> deletionDate,
            final DSLContext dsl,
            final KeycloakRPC user) {
        super(table, uuid, name, author, creationDate, deletionDate, dsl, user);
    }

    @Override
    public void transaction(final Consumer<Configuration> consumer) {
        dsl.transaction(consumer::accept);
    }

    @Override
    public E create(final E dto) {
        return execute(dto, insertQuery(dto));
    }

    @Override
    public E create(final Configuration configuration, final E dto) {
        return execute(dto, insertQuery(configuration, dto));
    }

    @Override
    public E update(final E dto) {
        return execute(dto, updateQuery(dto).where(uuid.eq(GUIDs.parse(dto))));
    }

    @Override
    public E update(final Configuration configuration, final E dto) {
        return execute(dto, updateQuery(configuration, dto).where(
            uuid.eq(GUIDs.parse(dto))));
    }

    @Override
    public void delete(final UUID uuid) {
        execute(uuid, deleteQuery(uuid));
    }

    @Override
    public void delete(final Configuration configuration, final UUID uuid) {
        execute(uuid, deleteQuery(configuration, uuid));
    }

    /**
     * Получение delete from запроса из таблицы.
     *
     * @param configuration Настройка транзакции.
     * @param uuid Уникальный идентификатор.
     * @return Запрос.
     */
    protected UpdateConditionStep<R> deleteQuery(
            final Configuration configuration,
            final UUID uuid) {
        return DSL.using(configuration)
                  .update(table)
                  .set(deletionDate, Dates.now())
                  .where(this.uuid.eq(uuid));
    }

    /**
     * Получение insert into запроса из таблицы.
     *
     * @param configuration Настройка транзакции.
     * @param dto Объект.
     * @return Запрос.
     */
    protected InsertSetMoreStep<R> insertQuery(
        final Configuration configuration,
        final E dto
    ) {
        return DSL
            .using(configuration)
            .insertInto(table)
            .set(uuid, GUIDs.parse(dto))
            .set(name, Strings.parse(dto.getName()))
            .set(creationDate, toJavaLocalDatetime(dto.getCreationDate()))
            .set(author, GUIDs.parse(dto.getAuthor()));
    }

    /**
     * Получение update запроса из таблицы.
     *
     * @param configuration Настройка транзакции.
     * @param dto Объект.
     * @return Запрос.
     */
    protected UpdateSetMoreStep<R> updateQuery(
        final Configuration configuration,
        final E dto
    ) {
        return DSL
            .using(configuration)
            .update(table)
            .set(name, Strings.parse(dto.getName()))
            .set(creationDate, toJavaLocalDatetime(dto.getCreationDate()))
            .set(author, GUIDs.parse(dto.getAuthor()));
    }

    /**
     * Получение delete from запроса из таблицы.
     *
     * @param uuid Уникальный идентификатор.
     * @return Запрос.
     */
    protected UpdateConditionStep<R> deleteQuery(final UUID uuid) {
        return dsl.transactionResult(
            configuration -> deleteQuery(configuration, uuid));
    }

    /**
     * Получение insert into запроса из таблицы.
     *
     * @param dto Объект.
     * @return Запрос.
     */
    protected InsertSetMoreStep<R> insertQuery(final E dto) {
        return dsl.transactionResult(
            configuration -> insertQuery(configuration, dto));
    }

    /**
     * Получение update запроса из таблицы.
     *
     * @param dto Объект.
     * @return Запрос.
     */
    protected UpdateSetMoreStep<R> updateQuery(final E dto) {
        return dsl.transactionResult(
            configuration -> updateQuery(configuration, dto));
    }

    /**
     * Исполнение запроса на обновление данных.
     *
     * @param dto Объект.
     * @param query Запрос.
     * @return Объект.
     */
    private E execute(final E dto, final Query query) {
        execute(GUIDs.parse(dto), query);

        return dto;
    }

    /**
     * Исполнение запроса на обновление данных.
     *
     * @param uuid Уникальный идентификатор объекта.
     * @param query Запрос.
     */
    private void execute(final UUID uuid, final Query query) {
        int count = query.execute();

        if (count == 0) {
            throw new NotFoundException(table, uuid);
        }

        if (count > 1) {
            throw new NotUniqueException(table, uuid);
        }
    }

}
