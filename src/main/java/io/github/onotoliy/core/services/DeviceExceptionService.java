package io.github.onotoliy.core.services;

import io.github.onotoliy.core.data.DeviceException;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.repositories.DeviceExceptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис управления ошибками устройства.
 *
 * @author Anatoliy Pokhresnyi
 */
@Service
public class DeviceExceptionService extends AbstractModifierService<
    DeviceException,
    SearchParameter,
    DeviceExceptionRepository> {

    /**
     * Конструктор.
     *
     * @param repository Репозиторий.
     */
    @Autowired
    public DeviceExceptionService(final DeviceExceptionRepository repository) {
        super(repository);
    }
}
