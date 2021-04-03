package com.jobportal.jobportal.dto;

import com.jobportal.jobportal.model.Postings;
import com.jobportal.jobportal.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class ApplicationsDto implements Serializable {

    private final Postings posting;
    private final User user;
    private final Date date_applied;
    private final int experience;
    private final String work_experience;
    private final String education;

}
