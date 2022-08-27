package io.github.onotoliy.core.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoZonedDateTime;
import java.util.Optional;

import org.jooq.Field;
import org.jooq.Record;

import static kotlinx.datetime.Instant.Companion;

/**
 * Утилитарный класс работы с датами.
 *
 * @author Anatoliy Pokhresnyi
 */
public final class Dates {

    /**
     * Конструктор.
     */
    private Dates() {

    }

    /**
     * Получает текущую дату.
     *
     * @return Текущая дата.
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * Чтение из {@link Record} значения колонки в формате {@link LocalDateTime}
     * и преобразование его в {@link String}.
     *
     * @param record Запись.
     * @param field Колонка.
     * @return Значение в формате {@link kotlinx.datetime.Instant}.
     */
    public static kotlinx.datetime.Instant format(
        final Record record,
        final Field<LocalDateTime> field
    ) {
        return toKotlinInstant(record.getValue(field, LocalDateTime.class));
    }

    /**
     * Преобразование {@link kotlinx.datetime.Instant value} в
     * {@link LocalDateTime}.
     *
     * @param value Значение в формате {@link kotlinx.datetime.Instant}.
     * @return Значение в формате {@link LocalDateTime}.
     */
    public static LocalDateTime toJavaLocalDatetime(
        final kotlinx.datetime.Instant value
    ) {
        return Optional
                .ofNullable(value)
                .map(kotlinx.datetime.Instant::toEpochMilliseconds)
                .map(Instant::ofEpochMilli)
                .map(e -> e.atZone(ZoneId.systemDefault()))
                .map(ZonedDateTime::toLocalDateTime)
                .orElse(null);
    }

    /**
     * Преобразование {@link LocalDateTime} в {@link kotlinx.datetime.Instant}.
     *
     * @param value Значение в формате {@link LocalDateTime}.
     * @return Значение в формате {@link kotlinx.datetime.Instant}.
     */
    public static kotlinx.datetime.Instant toKotlinInstant(
        final LocalDateTime value
    ) {
        return Optional
                .ofNullable(value)
                .map(e -> e.atZone(ZoneId.systemDefault()))
                .map(ChronoZonedDateTime::toInstant)
                .map(Instant::toEpochMilli)
                .map(Companion::fromEpochMilliseconds)
                .orElse(toKotlinInstant(LocalDateTime.now()));
    }
}
