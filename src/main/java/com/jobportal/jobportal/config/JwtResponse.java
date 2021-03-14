package com.jobportal.jobportal.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Object containing the information needed for a JWT Response.
 *
 * @since 13.03.2021
 */
@AllArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    private final String jwtToken;
}
