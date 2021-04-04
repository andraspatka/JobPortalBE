package com.jobportal.jobportal.exceptions;

/**
 * Exception occurs when inserting a posting which already exists in the db
 */
public class PostingAlreadyExistsException extends RuntimeException {
    public PostingAlreadyExistsException(String message) {
        super(message);
    }
}
