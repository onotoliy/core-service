package io.github.onotoliy.core.repositories;

import io.github.onotoliy.core.data.DeviceException;
import io.github.onotoliy.core.data.Option;
import io.github.onotoliy.core.data.SearchParameter;
import io.github.onotoliy.core.jooq.tables.CoreDeviceException;
import io.github.onotoliy.core.jooq.tables.records.CoreDeviceExceptionRecord;
import io.github.onotoliy.core.rpc.KeycloakRPC;
import io.github.onotoliy.core.utils.Dates;
import io.github.onotoliy.core.utils.GUIDs;
import io.github.onotoliy.core.utils.Strings;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.InsertSetMoreStep;
import org.jooq.Record;
import org.jooq.UpdateSetMoreStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static io.github.onotoliy.core.jooq.Tables.CORE_DEVICE_EXCEPTION;

/**
 * Репозиторий управления ошибками устройства.
 *
 * @author Anatoliy Pokhresnyi
 */
@Repository
public class DeviceExceptionRepository extends AbstractModifierRepository<
    DeviceException,
    SearchParameter,
    CoreDeviceExceptionRecord,
    CoreDeviceException> {

    /**
     * Конструктор.
     *
     * @param dsl Контекст подключения к БД.
     * @param user Сервис чтения пользователей.
     */
    @Autowired
    public DeviceExceptionRepository(
        final DSLContext dsl,
        final KeycloakRPC user
    ) {
        super(
            CORE_DEVICE_EXCEPTION,
            CORE_DEVICE_EXCEPTION.GUID,
            CORE_DEVICE_EXCEPTION.NAME,
            CORE_DEVICE_EXCEPTION.AUTHOR,
            CORE_DEVICE_EXCEPTION.CREATION_DATE,
            null,
            dsl,
            user
        );
    }

    @Override
    public InsertSetMoreStep<CoreDeviceExceptionRecord> insertQuery(
            final Configuration configuration,
            final DeviceException dto) {
        return super.insertQuery(configuration, dto)
                    .set(table.DEVICE, dto.getDevice())
                    .set(table.AGENT, dto.getAgent())
                    .set(table.MESSAGE, dto.getMessage())
                    .set(table.LOCALIZED_MESSAGE, dto.getLocalizedMessage())
                    .set(table.STACK_TRACE, dto.getStackTrace());
    }

    @Override
    public UpdateSetMoreStep<CoreDeviceExceptionRecord> updateQuery(
            final Configuration configuration,
            final DeviceException dto) {
        return super.updateQuery(configuration, dto)
                    .set(table.DEVICE, dto.getDevice())
                    .set(table.AGENT, dto.getAgent())
                    .set(table.MESSAGE, dto.getMessage())
                    .set(table.LOCALIZED_MESSAGE, dto.getLocalizedMessage())
                    .set(table.STACK_TRACE, dto.getStackTrace());
    }

    @Override
    protected DeviceException toDTO(final Record record) {
        return new DeviceException(
                GUIDs.format(record, table.GUID),
                Strings.format(record, table.DEVICE),
                Strings.format(record, table.AGENT),
                Strings.format(record, table.MESSAGE),
                Strings.format(record, table.LOCALIZED_MESSAGE),
                Strings.format(record, table.STACK_TRACE),
                Dates.format(record, table.CREATION_DATE),
                Strings.format(record, table.NAME),
                Optional.of(GUIDs.format(record, table.AUTHOR))
                        .filter(author -> !author.isEmpty())
                        .map(GUIDs::parse)
                        .map(user::find)
                        .orElse(new Option("", ""))
        );
    }
}
