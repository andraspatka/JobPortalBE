package com.jobportal.jobportal.exceptions;

/**
 * Exception used when a role does not correspond to a certain user
 *
 * @since 28.03.2021
 */
public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }

}
