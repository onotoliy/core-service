package io.github.onotoliy.core.repositories;

import io.github.onotoliy.core.data.Option;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.data.page.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Базовый репозиторий чтения записей из БД.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @author Anatoliy Pokhresnyi
 */
public interface ReaderRepository<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter> {

    /**
     * Получение опционального объекта.
     *
     * @param uuid Уникальный идентификатор.
     * @return Опциональный объект.
     */
    Optional<E> getOptional(UUID uuid);

    /**
     * Получение объекта.
     *
     * @param uuid Уникальный идентификатор.
     * @return Объект
     */
    E get(UUID uuid);

    /**
     * Получение списка всех объектов.
     *
     * @return Объекты.
     */
    List<Option> getAll();

    /**
     * Поиск объектов.
     *
     * @param parameter Поисковые параметры.
     * @return Объекты.
     */
    Page<E> getAll(P parameter);

}
