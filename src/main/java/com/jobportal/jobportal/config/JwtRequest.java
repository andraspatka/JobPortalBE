package com.jobportal.jobportal.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Object containing the information needed for a JWT Request.
 *
 * @since 13.03.2021
 */
@AllArgsConstructor
@Getter
public class JwtRequest implements Serializable {

    private final String email;
    private final String password;
}
