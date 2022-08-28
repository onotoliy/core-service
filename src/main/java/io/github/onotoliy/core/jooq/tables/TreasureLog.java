/*
 * This file is generated by jOOQ.
 */
package io.github.onotoliy.core.jooq.tables;


import io.github.onotoliy.core.jooq.Keys;
import io.github.onotoliy.core.jooq.Public;
import io.github.onotoliy.core.jooq.tables.records.TreasureLogRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Логи
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TreasureLog extends TableImpl<TreasureLogRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.treasure_log</code>
     */
    public static final TreasureLog TREASURE_LOG = new TreasureLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TreasureLogRecord> getRecordType() {
        return TreasureLogRecord.class;
    }

    /**
     * The column <code>public.treasure_log.guid</code>. Уникальный идентификатор
     */
    public final TableField<TreasureLogRecord, UUID> GUID = createField(DSL.name("guid"), SQLDataType.UUID.nullable(false), this, "Уникальный идентификатор");

    /**
     * The column <code>public.treasure_log.author</code>. Автор
     */
    public final TableField<TreasureLogRecord, UUID> AUTHOR = createField(DSL.name("author"), SQLDataType.UUID, this, "Автор");

    /**
     * The column <code>public.treasure_log.level</code>. Уровень логирования
     */
    public final TableField<TreasureLogRecord, String> LEVEL = createField(DSL.name("level"), SQLDataType.VARCHAR, this, "Уровень логирования");

    /**
     * The column <code>public.treasure_log.creation_date</code>. Дата создания записи
     */
    public final TableField<TreasureLogRecord, LocalDateTime> CREATION_DATE = createField(DSL.name("creation_date"), SQLDataType.LOCALDATETIME(6), this, "Дата создания записи");

    /**
     * The column <code>public.treasure_log.message</code>. Сообщение
     */
    public final TableField<TreasureLogRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR, this, "Сообщение");

    /**
     * The column <code>public.treasure_log.bean_name</code>. Название класса
     */
    public final TableField<TreasureLogRecord, String> BEAN_NAME = createField(DSL.name("bean_name"), SQLDataType.VARCHAR, this, "Название класса");

    /**
     * The column <code>public.treasure_log.stack_trace</code>. Stack Trace
     */
    public final TableField<TreasureLogRecord, String> STACK_TRACE = createField(DSL.name("stack_trace"), SQLDataType.VARCHAR, this, "Stack Trace");

    private TreasureLog(Name alias, Table<TreasureLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private TreasureLog(Name alias, Table<TreasureLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Логи"), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.treasure_log</code> table reference
     */
    public TreasureLog(String alias) {
        this(DSL.name(alias), TREASURE_LOG);
    }

    /**
     * Create an aliased <code>public.treasure_log</code> table reference
     */
    public TreasureLog(Name alias) {
        this(alias, TREASURE_LOG);
    }

    /**
     * Create a <code>public.treasure_log</code> table reference
     */
    public TreasureLog() {
        this(DSL.name("treasure_log"), null);
    }

    public <O extends Record> TreasureLog(Table<O> child, ForeignKey<O, TreasureLogRecord> key) {
        super(child, key, TREASURE_LOG);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<TreasureLogRecord> getPrimaryKey() {
        return Keys.TREASURE_LOG_PKEY;
    }

    @Override
    public List<UniqueKey<TreasureLogRecord>> getKeys() {
        return Arrays.<UniqueKey<TreasureLogRecord>>asList(Keys.TREASURE_LOG_PKEY);
    }

    @Override
    public TreasureLog as(String alias) {
        return new TreasureLog(DSL.name(alias), this);
    }

    @Override
    public TreasureLog as(Name alias) {
        return new TreasureLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TreasureLog rename(String name) {
        return new TreasureLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public TreasureLog rename(Name name) {
        return new TreasureLog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, UUID, String, LocalDateTime, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
