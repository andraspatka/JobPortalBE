package com.jobportal.jobportal.dto;

import com.jobportal.jobportal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Dto for a User.
 *
 * @since 13.03.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class UserDto implements Serializable {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String company;
    private final Role role;
}
