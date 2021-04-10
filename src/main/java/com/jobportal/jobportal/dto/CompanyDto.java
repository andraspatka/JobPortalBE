package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * Dto for the company.
 *
 * @since 09.04.2021
 * */
@Builder
@Getter
@AllArgsConstructor
public class CompanyDto implements Serializable {
    private final String name;
}
