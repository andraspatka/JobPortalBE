package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Dto for complete posting object. This class is used when list of postings
 * is returned via get request
 * @since 04.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class PostingCompleteDto implements Serializable {

    private final Long id;
    private final Long postedBy;
    private final LocalDate postedAt;
    private final LocalDate deadline;
    private final long numberOfViews;
    private final String name;
    private final String description;
    private final Long category;
    private final String requirements;
}
