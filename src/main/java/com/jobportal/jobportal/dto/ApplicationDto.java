package com.jobportal.jobportal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;


/**
 * ApplicationDto containing all the information related to
 * a an application, including its author Id and connected posting Id
 *
 * @since 11.04.2021
 */
@Builder
@Getter
@AllArgsConstructor
public class ApplicationDto implements Serializable {

    private final Date applicationDate;
    private final Integer numberYearsExperience;
    private final String workingExperience;
    private final String education;
    private final Long applicantId;
    private final Long postingId;
}
