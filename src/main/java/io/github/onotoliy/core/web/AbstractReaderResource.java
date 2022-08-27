package io.github.onotoliy.core.web;

import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.services.ReaderService;

import java.util.UUID;

/**
 * Базовый WEB сервис чтения данных.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры объекта.
 * @param <S> Сервис чтения записей.
 * @author Anatoliy Pokhresnyi
 */
public abstract class AbstractReaderResource<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter,
    S extends ReaderService<E, P>>
implements ReaderResource<E> {

    /**
     * Сервис чтения записей.
     */
    protected final S service;

    /**
     * Конструктор.
     *
     * @param service Сервис чтения записей.
     */
    public AbstractReaderResource(final S service) {
        this.service = service;
    }

    @Override
    public E get(final UUID uuid) {
        return service.get(uuid);
    }

}
