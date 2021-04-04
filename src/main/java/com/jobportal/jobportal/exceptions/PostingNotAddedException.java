package com.jobportal.jobportal.exceptions;

/**
 * Exception is thrown when posting could not be saved in the database
 * @since 03.04.2021
 */
public class PostingNotAddedException extends RuntimeException {
    public PostingNotAddedException(String message) {
        super(message);
    }
}
