package io.github.onotoliy.core.services;

import io.github.onotoliy.core.data.Option;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.data.page.Page;
import io.github.onotoliy.core.bpp.log.Log;

import java.util.List;
import java.util.UUID;

/**
 * Базовый сервис чтения объектов.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @author Anatoliy Pokhresnyi
 */
public interface ReaderService<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter> {

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

    /**
     * Получение версии сущности.
     *
     * @return Версия сущности.
     */
    @Log(db = true)
    Option version();

    /**
     * Данные, которые необходимо синхронизировать.
     *
     * @param version Версия объекта.
     * @param offset Количество записей которое необходимо пропустить.
     * @param numberOfRows Размер страницы.
     * @return Данные, которые необходимо синхронизировать.
     */
    @Log(db = true)
    Page<E> sync(long version, int offset, int numberOfRows);
}
