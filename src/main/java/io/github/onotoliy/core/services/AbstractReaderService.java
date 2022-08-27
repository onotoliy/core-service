package io.github.onotoliy.core.services;

import io.github.onotoliy.core.data.Option;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.data.page.Page;
import io.github.onotoliy.core.repositories.ReaderRepository;

import java.util.List;
import java.util.UUID;

/**
 * Базовый сервис чтения объектов.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры.
 * @param <R> Запись из БД
 * @author Anatoliy Pokhresnyi
 */
public abstract class AbstractReaderService<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter,
    R extends ReaderRepository<E, P>>
implements ReaderService<E, P> {

    /**
     * Репозиторий.
     */
    protected final R repository;

    /**
     * Констрктор.
     *
     * @param repository Репозиторий.
     */
    public AbstractReaderService(final R repository) {
        this.repository = repository;
    }

    @Override
    public E get(final UUID uuid) {
        return repository.get(uuid);
    }

    @Override
    public List<Option> getAll() {
        return repository.getAll();
    }

    @Override
    public Page<E> getAll(final P parameter) {
        return repository.getAll(parameter);
    }

}
