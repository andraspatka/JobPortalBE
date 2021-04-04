package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * PostingSimpleDto containing only the fields for update
 * @since 03.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class PostingSimpleDto implements Serializable {

    private final Long id;
    private final LocalDate deadline;
    private final String name;
    private final String description;
    private final Integer category;
    private final String requirements;
}
