package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * CategoryDto containing name of category
 * @since 03.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class CategoryDto implements Serializable {
    private final String name;
}
