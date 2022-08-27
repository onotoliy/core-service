package io.github.onotoliy.core.web;

import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.data.HasAuthor;
import io.github.onotoliy.core.data.HasCreationDate;
import io.github.onotoliy.core.data.HasName;
import io.github.onotoliy.core.data.HasUID;
import io.github.onotoliy.core.services.ModifierService;

import java.util.UUID;

/**
 * Базовый WEB сервис управления данными.
 *
 * @param <E> Объект.
 * @param <P> Поисковые параметры объекта.
 * @param <S> Сервис чтения записей.
 * @author Anatoliy Pokhresnyi
 */
public abstract class AbstractModifierResource<
    E extends HasUID & HasName & HasCreationDate & HasAuthor,
    P extends SearchParameter,
    S extends ModifierService<E, P>>
extends AbstractReaderResource<E, P, S>
implements ModifierResource<E> {

    /**
     * Конструктор.
     *
     * @param service Сервис.
     */
    public AbstractModifierResource(final S service) {
        super(service);
    }

    @Override
    public E create(final E dto) {
        return service.create(dto);
    }

    @Override
    public E update(final E dto) {
        return service.update(dto);
    }

    @Override
    public void delete(final UUID uuid) {
        service.delete(uuid);
    }

}
