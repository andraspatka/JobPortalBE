package com.jobportal.jobportal.dto;

import com.jobportal.jobportal.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Dto containing the authentication details of a user.
 *
 * @since 13.03.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class AuthenticationDto implements Serializable {

    private final Long id;
    private final Role role;
    private final String email;
    private final String password;
}
