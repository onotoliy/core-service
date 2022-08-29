/*
 * This file is generated by jOOQ.
 */
package io.github.onotoliy.core.jooq.tables;


import io.github.onotoliy.core.jooq.DefaultSchema;
import io.github.onotoliy.core.jooq.Keys;
import io.github.onotoliy.core.jooq.tables.records.CoreDeviceExceptionRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Описание ошибки устройства
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreDeviceException extends TableImpl<CoreDeviceExceptionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>CORE_DEVICE_EXCEPTION</code>
     */
    public static final CoreDeviceException CORE_DEVICE_EXCEPTION = new CoreDeviceException();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CoreDeviceExceptionRecord> getRecordType() {
        return CoreDeviceExceptionRecord.class;
    }

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.GUID</code>. Уникальный идентификатор
     */
    public final TableField<CoreDeviceExceptionRecord, UUID> GUID = createField(DSL.name("GUID"), SQLDataType.UUID.nullable(false), this, "Уникальный идентификатор");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.NAME</code>. Название
     */
    public final TableField<CoreDeviceExceptionRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(2147483647), this, "Название");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.AUTHOR</code>. Автор
     */
    public final TableField<CoreDeviceExceptionRecord, UUID> AUTHOR = createField(DSL.name("AUTHOR"), SQLDataType.UUID, this, "Автор");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.CREATION_DATE</code>. Дата создания
     */
    public final TableField<CoreDeviceExceptionRecord, LocalDateTime> CREATION_DATE = createField(DSL.name("CREATION_DATE"), SQLDataType.LOCALDATETIME(6), this, "Дата создания");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.DELETION_DATE</code>. Дата удаления
     */
    public final TableField<CoreDeviceExceptionRecord, LocalDateTime> DELETION_DATE = createField(DSL.name("DELETION_DATE"), SQLDataType.LOCALDATETIME(6), this, "Дата удаления");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.DEVICE</code>. Устройство
     */
    public final TableField<CoreDeviceExceptionRecord, String> DEVICE = createField(DSL.name("DEVICE"), SQLDataType.VARCHAR(2147483647), this, "Устройство");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.AGENT</code>. Агент
     */
    public final TableField<CoreDeviceExceptionRecord, String> AGENT = createField(DSL.name("AGENT"), SQLDataType.VARCHAR(2147483647), this, "Агент");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.MESSAGE</code>. Сообщение об ошибки
     */
    public final TableField<CoreDeviceExceptionRecord, String> MESSAGE = createField(DSL.name("MESSAGE"), SQLDataType.VARCHAR(2147483647), this, "Сообщение об ошибки");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.LOCALIZED_MESSAGE</code>. Локализированое сообщение об ошибки
     */
    public final TableField<CoreDeviceExceptionRecord, String> LOCALIZED_MESSAGE = createField(DSL.name("LOCALIZED_MESSAGE"), SQLDataType.VARCHAR(2147483647), this, "Локализированое сообщение об ошибки");

    /**
     * The column <code>CORE_DEVICE_EXCEPTION.STACK_TRACE</code>. StackTrace
     */
    public final TableField<CoreDeviceExceptionRecord, String> STACK_TRACE = createField(DSL.name("STACK_TRACE"), SQLDataType.VARCHAR(2147483647), this, "StackTrace");

    private CoreDeviceException(Name alias, Table<CoreDeviceExceptionRecord> aliased) {
        this(alias, aliased, null);
    }

    private CoreDeviceException(Name alias, Table<CoreDeviceExceptionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Описание ошибки устройства"), TableOptions.table());
    }

    /**
     * Create an aliased <code>CORE_DEVICE_EXCEPTION</code> table reference
     */
    public CoreDeviceException(String alias) {
        this(DSL.name(alias), CORE_DEVICE_EXCEPTION);
    }

    /**
     * Create an aliased <code>CORE_DEVICE_EXCEPTION</code> table reference
     */
    public CoreDeviceException(Name alias) {
        this(alias, CORE_DEVICE_EXCEPTION);
    }

    /**
     * Create a <code>CORE_DEVICE_EXCEPTION</code> table reference
     */
    public CoreDeviceException() {
        this(DSL.name("CORE_DEVICE_EXCEPTION"), null);
    }

    public <O extends Record> CoreDeviceException(Table<O> child, ForeignKey<O, CoreDeviceExceptionRecord> key) {
        super(child, key, CORE_DEVICE_EXCEPTION);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public UniqueKey<CoreDeviceExceptionRecord> getPrimaryKey() {
        return Keys.PK_CORE_DEVICE_EXCEPTION;
    }

    @Override
    public List<UniqueKey<CoreDeviceExceptionRecord>> getKeys() {
        return Arrays.<UniqueKey<CoreDeviceExceptionRecord>>asList(Keys.PK_CORE_DEVICE_EXCEPTION);
    }

    @Override
    public CoreDeviceException as(String alias) {
        return new CoreDeviceException(DSL.name(alias), this);
    }

    @Override
    public CoreDeviceException as(Name alias) {
        return new CoreDeviceException(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreDeviceException rename(String name) {
        return new CoreDeviceException(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CoreDeviceException rename(Name name) {
        return new CoreDeviceException(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<UUID, String, UUID, LocalDateTime, LocalDateTime, String, String, String, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
