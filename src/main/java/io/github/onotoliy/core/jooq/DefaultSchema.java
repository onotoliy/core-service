/*
 * This file is generated by jOOQ.
 */
package io.github.onotoliy.core.jooq;


import io.github.onotoliy.core.jooq.tables.CoreDeviceException;
import io.github.onotoliy.core.jooq.tables.CoreLog;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DefaultSchema extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>DEFAULT_SCHEMA</code>
     */
    public static final DefaultSchema DEFAULT_SCHEMA = new DefaultSchema();

    /**
     * Описание ошибки устройства
     */
    public final CoreDeviceException CORE_DEVICE_EXCEPTION = CoreDeviceException.CORE_DEVICE_EXCEPTION;

    /**
     * Логи
     */
    public final CoreLog CORE_LOG = CoreLog.CORE_LOG;

    /**
     * No further instances allowed
     */
    private DefaultSchema() {
        super("", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            CoreDeviceException.CORE_DEVICE_EXCEPTION,
            CoreLog.CORE_LOG);
    }
}