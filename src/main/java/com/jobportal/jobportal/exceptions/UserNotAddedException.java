package com.jobportal.jobportal.exceptions;

/**
 * Exception used when an User could not be added in the
 * database.
 *
 * @since 13.03.2021
 */
public class UserNotAddedException extends RuntimeException {

    public UserNotAddedException(String message) {
        super(message);
    }
}
