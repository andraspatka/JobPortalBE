package com.jobportal.jobportal.exceptions;

/**
 * Exception occurs when Category already exists
 * @since 03.04.2021
 */
public class CategoryAlreadyExistsException extends RuntimeException{

    public CategoryAlreadyExistsException(String message) {
        super(message);
    }
}
