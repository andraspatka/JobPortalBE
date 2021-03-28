package com.jobportal.jobportal.exceptions;

/**
 * Exception used when an employer has a company that does not
 * exist.
 *
 * @since 27.03.2021
 */
public class CompanyNotExistingException extends RuntimeException{
    public CompanyNotExistingException(String message) {
        super(message);
    }
}
