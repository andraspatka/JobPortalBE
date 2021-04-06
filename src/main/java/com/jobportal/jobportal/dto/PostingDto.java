package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * PostingDto containing all posting fields except for id
 * @since 03.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class PostingDto implements Serializable {

    private final Long postedBy;
    private final LocalDate postedAt;
    private final LocalDate deadline;
    private final long numberOfViews;
    private final String name;
    private final String description;
    private final Long category;
    private final String requirements;
}
