package com.jobportal.jobportal.exceptions;

/**
 * Exception occurs when the id/email corresponds to no
 * user of the job portal.
 *
 * @since 09.04.2021
 */
public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String message) {
        super(message);
    }
}
