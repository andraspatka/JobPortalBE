package com.jobportal.jobportal.exceptions;

/**
 * Exception occurs when category could not be added
 * @since 03.04.2021
 */
public class CategoryNotAddedException extends RuntimeException{
    public CategoryNotAddedException(String message) {
        super(message);
    }
}
