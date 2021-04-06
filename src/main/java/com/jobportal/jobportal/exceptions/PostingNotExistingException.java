package com.jobportal.jobportal.exceptions;

/**
 * Exception is thrown when posting does not exists
 * and one tries to execute a operation on it
 * @since 03.04.2021
 */
public class PostingNotExistingException extends RuntimeException {
    public PostingNotExistingException(String message) {
        super(message);
    }
}
