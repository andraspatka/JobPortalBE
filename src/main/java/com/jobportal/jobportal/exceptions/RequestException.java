package com.jobportal.jobportal.exceptions;

/**
 * Exception used when an unexpected exception is
 * related to a {@link com.jobportal.jobportal.model.Request}
 *
 * @since 27.03.2021
 */
public class RequestException extends RuntimeException {
    public RequestException(String message) {
        super(message);
    }
}
