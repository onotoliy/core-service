package io.github.onotoliy.core.utils;

import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.Record;

/**
 * Утилитарный класс работы с логическими переменными.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class Booleans {

    /**
     * Конструктор.
     */
    private Booleans() {

    }

    /**
     * Преобразование {@link String} в {@link Boolean}.
     *
     * @param value Значение в формате {@link Boolean}.
     * @return Значение в формате {@link Boolean}.
     */
    public static boolean format(final @Nullable Boolean value) {
        return value == null ? false : value;
    }

    /**
     * Чтение из {@link Record} значения колонки в формате {@link Boolean} и
     * преобразование его в {@link Boolean}.
     *
     * @param record Запись.
     * @param field Колонка.
     * @return Значение в формате {@link Boolean}.
     */
    public static Boolean format(final Record record,
                                 final Field<Boolean> field) {
        return format(record.getValue(field, Boolean.class));
    }
}
