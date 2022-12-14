/*
 * This file is generated by jOOQ.
 */
package io.github.onotoliy.core.jooq.tables.records;


import io.github.onotoliy.core.jooq.tables.CoreLog;

import java.time.LocalDateTime;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * Логи
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoreLogRecord extends UpdatableRecordImpl<CoreLogRecord> implements Record7<UUID, UUID, String, LocalDateTime, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>CORE_LOG.GUID</code>. Уникальный идентификатор
     */
    public void setGuid(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>CORE_LOG.GUID</code>. Уникальный идентификатор
     */
    public UUID getGuid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>CORE_LOG.AUTHOR</code>. Автор
     */
    public void setAuthor(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>CORE_LOG.AUTHOR</code>. Автор
     */
    public UUID getAuthor() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>CORE_LOG.LEVEL</code>. Уровень логирования
     */
    public void setLevel(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>CORE_LOG.LEVEL</code>. Уровень логирования
     */
    public String getLevel() {
        return (String) get(2);
    }

    /**
     * Setter for <code>CORE_LOG.CREATION_DATE</code>. Дата создания записи
     */
    public void setCreationDate(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>CORE_LOG.CREATION_DATE</code>. Дата создания записи
     */
    public LocalDateTime getCreationDate() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>CORE_LOG.MESSAGE</code>. Сообщение
     */
    public void setMessage(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>CORE_LOG.MESSAGE</code>. Сообщение
     */
    public String getMessage() {
        return (String) get(4);
    }

    /**
     * Setter for <code>CORE_LOG.BEAN_NAME</code>. Название класса
     */
    public void setBeanName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>CORE_LOG.BEAN_NAME</code>. Название класса
     */
    public String getBeanName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>CORE_LOG.STACK_TRACE</code>. Stack Trace
     */
    public void setStackTrace(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>CORE_LOG.STACK_TRACE</code>. Stack Trace
     */
    public String getStackTrace() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, UUID, String, LocalDateTime, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<UUID, UUID, String, LocalDateTime, String, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return CoreLog.CORE_LOG.GUID;
    }

    @Override
    public Field<UUID> field2() {
        return CoreLog.CORE_LOG.AUTHOR;
    }

    @Override
    public Field<String> field3() {
        return CoreLog.CORE_LOG.LEVEL;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return CoreLog.CORE_LOG.CREATION_DATE;
    }

    @Override
    public Field<String> field5() {
        return CoreLog.CORE_LOG.MESSAGE;
    }

    @Override
    public Field<String> field6() {
        return CoreLog.CORE_LOG.BEAN_NAME;
    }

    @Override
    public Field<String> field7() {
        return CoreLog.CORE_LOG.STACK_TRACE;
    }

    @Override
    public UUID component1() {
        return getGuid();
    }

    @Override
    public UUID component2() {
        return getAuthor();
    }

    @Override
    public String component3() {
        return getLevel();
    }

    @Override
    public LocalDateTime component4() {
        return getCreationDate();
    }

    @Override
    public String component5() {
        return getMessage();
    }

    @Override
    public String component6() {
        return getBeanName();
    }

    @Override
    public String component7() {
        return getStackTrace();
    }

    @Override
    public UUID value1() {
        return getGuid();
    }

    @Override
    public UUID value2() {
        return getAuthor();
    }

    @Override
    public String value3() {
        return getLevel();
    }

    @Override
    public LocalDateTime value4() {
        return getCreationDate();
    }

    @Override
    public String value5() {
        return getMessage();
    }

    @Override
    public String value6() {
        return getBeanName();
    }

    @Override
    public String value7() {
        return getStackTrace();
    }

    @Override
    public CoreLogRecord value1(UUID value) {
        setGuid(value);
        return this;
    }

    @Override
    public CoreLogRecord value2(UUID value) {
        setAuthor(value);
        return this;
    }

    @Override
    public CoreLogRecord value3(String value) {
        setLevel(value);
        return this;
    }

    @Override
    public CoreLogRecord value4(LocalDateTime value) {
        setCreationDate(value);
        return this;
    }

    @Override
    public CoreLogRecord value5(String value) {
        setMessage(value);
        return this;
    }

    @Override
    public CoreLogRecord value6(String value) {
        setBeanName(value);
        return this;
    }

    @Override
    public CoreLogRecord value7(String value) {
        setStackTrace(value);
        return this;
    }

    @Override
    public CoreLogRecord values(UUID value1, UUID value2, String value3, LocalDateTime value4, String value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoreLogRecord
     */
    public CoreLogRecord() {
        super(CoreLog.CORE_LOG);
    }

    /**
     * Create a detached, initialised CoreLogRecord
     */
    public CoreLogRecord(UUID guid, UUID author, String level, LocalDateTime creationDate, String message, String beanName, String stackTrace) {
        super(CoreLog.CORE_LOG);

        setGuid(guid);
        setAuthor(author);
        setLevel(level);
        setCreationDate(creationDate);
        setMessage(message);
        setBeanName(beanName);
        setStackTrace(stackTrace);
    }
}
