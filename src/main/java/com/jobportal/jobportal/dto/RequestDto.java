package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Dto for the request to become an employer
 *
 * @since 09.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class RequestDto implements Serializable {

    private final Long id;
    private final String requestedByFirstName;
    private final String requestedByLastName;
    private final String requestedByEmail;
}
