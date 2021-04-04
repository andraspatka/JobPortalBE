package com.jobportal.jobportal.exceptions;

/**
 * Exception is thrown when posting could not be edited
 * @since 03.04.2021
 */
public class PostingNotEditedException extends RuntimeException {
    public PostingNotEditedException(String message) {
        super(message);
    }
}
