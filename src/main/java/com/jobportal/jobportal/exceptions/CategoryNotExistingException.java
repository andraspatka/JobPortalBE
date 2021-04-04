package com.jobportal.jobportal.exceptions;

/**
 * Exception is thrown when a posting contains a non-existing category
 *
 * @since 03.04.2021
 */
public class CategoryNotExistingException extends RuntimeException{

    public CategoryNotExistingException(String message) {
        super(message);
    }
}
