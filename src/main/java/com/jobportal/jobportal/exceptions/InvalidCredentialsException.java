package com.jobportal.jobportal.exceptions;

/**
 * Exception used when an User added bad credentails
 * when trying to log in.
 *
 * @since 13.03.2021
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
