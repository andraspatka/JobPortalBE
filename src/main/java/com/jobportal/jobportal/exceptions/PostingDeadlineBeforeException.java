package com.jobportal.jobportal.exceptions;

/**
 * Exception occurs when Deadline date is set before the date when posting is created
 *
 * @since 03.04.2021
 */
public class PostingDeadlineBeforeException extends RuntimeException {
    public PostingDeadlineBeforeException(String message) {
        super(message);
    }
}
