package com.jobportal.jobportal.model;

import lombok.Getter;

/**
 * Contains all the possible User Roles
 * of the application.
 *
 * @since 13.03.2021
 */
public enum Role {
    EMPLOYER(0),
    EMPLOYEE(1),
    ADMIN(2);

    @Getter
    private int roleType;

    Role(int roleType) {
        this.roleType = roleType;
    }
}
