package io.github.onotoliy.core.services;

import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.bpp.log.Log;

import java.util.UUID;

/**
 * Интерфейс базового сервиса управления объектами.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @author Anatoliy Pokhresnyi
 */
public interface ModifierService<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter>
extends ReaderService<E, P> {

    /**
     * Создание объекта.
     *
     * @param dto Объект.
     * @return Объект.
     */
    @Log(db = true)
    E create(E dto);

    /**
     * Изменение объекта.
     *
     * @param dto Объект.
     * @return Объект.
     */
    @Log(db = true)
    E update(E dto);

    /**
     * Удаление объекта.
     *
     * @param uuid Уникальный идентификатор.
     */
    @Log(db = true)
    void delete(UUID uuid);

}
