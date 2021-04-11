package com.jobportal.jobportal.exceptions;

/**
 * Exception used when an unexpected exception is
 * related to a {@link com.jobportal.jobportal.model.Application}
 *
 * @since 11.04.2021
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }
}
