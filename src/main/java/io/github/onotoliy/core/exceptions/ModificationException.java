package io.github.onotoliy.core.exceptions;

/**
 * Ошибка изменения данных.
 *
 * @author Anatoliy Pokhresnyi
 */
public class ModificationException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message Сообщение об ошибке.
     */
    public ModificationException(final String message) {
        super(message);
    }
}
