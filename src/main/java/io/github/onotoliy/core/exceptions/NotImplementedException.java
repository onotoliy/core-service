package io.github.onotoliy.core.exceptions;

/**
 * Ошибка не реализованного метода.
 *
 * @author Anatoliy Pokhresnyi
 */
public class NotImplementedException extends RuntimeException {

    /**
     * Конструктор.
     *
     * @param message Сообщение об ошибке.
     */
    public NotImplementedException(final String message) {
        super(message);
    }
}
