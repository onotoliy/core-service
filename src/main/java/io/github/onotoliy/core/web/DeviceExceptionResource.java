package io.github.onotoliy.core.web;

import io.github.onotoliy.core.data.DeviceException;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.exceptions.NotImplementedException;
import io.github.onotoliy.core.services.DeviceExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/device/exception")
public class DeviceExceptionResource extends AbstractModifierResource<
    DeviceException,
    SearchParameter,
    DeviceExceptionService> {

    /**
     * Конструктор.
     *
     * @param service Сервис.
     */
    @Autowired
    public DeviceExceptionResource(final DeviceExceptionService service) {
        super(service);
    }

    @Override
    public DeviceException update(final DeviceException dto) {
        throw new NotImplementedException(
            "Метод  изменения ошибки устройства не реализован.");
    }

    @Override
    public void delete(final UUID uuid) {
        throw new NotImplementedException(
            "Метод  удаления ошибки устройства не реализован.");
    }
}
