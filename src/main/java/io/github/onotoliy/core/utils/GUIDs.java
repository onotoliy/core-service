package io.github.onotoliy.core.utils;

import io.github.onotoliy.core.data.HasUID;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.jooq.Field;
import org.jooq.Record;

/**
 * Утилитарный класс работы с уникальными идентификаторами.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class GUIDs {

    /**
     * Конструктор.
     */
    private GUIDs() {

    }

    /**
     * Чтение из {@link Record} значения колонки в формате {@link UUID} и
     * преобразование его в {@link String}.
     *
     * @param record Запись.
     * @param field Колонка.
     * @return Значение в формате {@link java.lang.String}.
     */
    public static String format(final Record record, final Field<UUID> field) {
        return format(record.getValue(field, UUID.class));
    }

    /**
     * Преобразование {@link UUID} в {@link String}.
     *
     * @param value Значение в формате {@link UUID}.
     * @return Значение в формате {@link String}.
     */
    @NotNull
    public static String format(final UUID value) {
        return value == null ? "" : value.toString();
    }

    /**
     * Преобразование {@link HasUID} в {@link UUID}.
     *
     * @param value Значение в формате {@link HasUID}.
     * @return Значение в формате {@link UUID}.
     */
    public static UUID parse(final HasUID value) {
        return value == null ? null : parse(value.getUid());
    }

    /**
     * Получение случайного уникального идентификатора.
     *
     * @return Случайный уникальный идентификатор.
     */
    @NotNull
    public static UUID random() {
        return UUID.randomUUID();
    }

    /**
     * Преобразование {@link String} в {@link UUID}.
     *
     * @param value Значение в формате {@link String}.
     * @return Значение в формате {@link UUID}.
     */
    @NotNull
    public static UUID parse(@NotNull final String value) {
        return UUID.fromString(value);
    }

    /**
     * Проверяет объект на пустоту.
     *
     * @param value Объект
     * @return Результат проверки.
     */
    public static boolean isEmpty(final UUID value) {
        return value == null;
    }

    /**
     * Проверка содержит ли объект какое-либо значение.
     *
     * @param value Объект.
     * @return Результат проверки.
     */
    public static boolean nonEmpty(final UUID value) {
        return !isEmpty(value);
    }

    /**
     * Проверяет объект на пустоту.
     *
     * @param value Объект
     * @return Результат проверки.
     */
    public static boolean isEmpty(final HasUID value) {
        return value == null || Strings.isEmpty(value.getUid());
    }

    /**
     * Проверка содержит ли объект какое-либо значение.
     *
     * @param value Объект.
     * @return Результат проверки.
     */
    public static boolean nonEmpty(final HasUID value) {
        return !isEmpty(value);
    }

    /**
     * Проверка двух объектов на неравенство.
     *
     * @param x X
     * @param y Y
     * @return Результат проверки.
     */
    public static boolean nonEqually(final HasUID x, final HasUID y) {
        return !isEqually(x, y);
    }

    /**
     * Проверка двух объектов на равенство.
     *
     * @param x X
     * @param y Y
     * @return Результат проверки.
     */
    public static boolean isEqually(final HasUID x, final HasUID y) {
        return Objects.isEqually(parse(x), parse(y), UUID::compareTo);
    }
}
