package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.ApplicationDto;
import com.jobportal.jobportal.exceptions.ApplicationException;
import com.jobportal.jobportal.exceptions.PostingNotExistingException;
import com.jobportal.jobportal.exceptions.UnknownUserException;
import com.jobportal.jobportal.service.ApplicationService;
import com.jobportal.openapi.api.ApplicationsApi;
import com.jobportal.openapi.model.ApplicationInformation;
import com.jobportal.openapi.model.AuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.assertj.core.util.Lists;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for applications to jobs.
 *
 * @since 11.04.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ApplicationController implements ApplicationsApi {

    private static final String APPLICATION_ADDED_MESSAGE = "Application was successfully added.";
    private static final String APPLICATION_UNAPPLIED_MESSAGE = "You unapplied successfully from the posting.";

    private final ApplicationService applicationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> applicationsIdDelete(Long applicationId) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            applicationService.unapplyFromJob(applicationId);
            response.setBody(APPLICATION_UNAPPLIED_MESSAGE);
            response.setStatus(HttpStatus.OK);
        } catch (ApplicationException exception) {
            response.setStatus(HttpStatus.NOT_ACCEPTABLE);
            response.setBody(exception.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> applicationsPost(
            @Valid ApplicationInformation applicationInformation) {
        final ApplicationDto applicationDto = buildApplicationDto(applicationInformation);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setStatus(HttpStatus.OK);
        try {
            applicationService.applyToAJob(applicationDto);
            response.setBody(APPLICATION_ADDED_MESSAGE);
        } catch (UnknownUserException | PostingNotExistingException exception) {
            response.setBody(exception.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<ApplicationInformation>> applicationsPostingIdGet(Long postingId) {
        try {
            List<ApplicationInformation> applicationInformationList =
                    buildApplicationInformations(applicationService.getAllApplicationsForPosting(postingId));
            return ResponseEntity.ok(applicationInformationList);
        } catch (PostingNotExistingException exception) {
            return ResponseEntity.ok(Lists.emptyList());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<ApplicationInformation>> applicationsUserIdGet(Long userId) {
        try {
            List<ApplicationInformation> applicationInformationList =
                    buildApplicationInformations(applicationService.getAllApplicationsForUser(userId));
            return ResponseEntity.ok(applicationInformationList);
        } catch (UnknownUserException exception) {
            return ResponseEntity.ok(Lists.emptyList());
        }
    }

    private ApplicationDto buildApplicationDto(@Valid ApplicationInformation applicationInformation) {
        return ApplicationDto.builder()
                .postingId(applicationInformation.getPostingId())
                .workingExperience(applicationInformation.getWorkingExperience())
                .numberYearsExperience(applicationInformation.getNumberYearsExperience())
                .education(applicationInformation.getEducation())
                .applicantId(applicationInformation.getApplicantId())
                .applicationDate(LocalDate.now().toDate())
                .build();
    }

    private List<ApplicationInformation> buildApplicationInformations(List<ApplicationDto> applicationDtos) {
        return applicationDtos.stream()
                .map(this::buildApplicationInformation)
                .collect(Collectors.toList());
    }

    private ApplicationInformation buildApplicationInformation(@NonNull ApplicationDto applicationDto) {
        ApplicationInformation applicationInformation = new ApplicationInformation();
        applicationInformation.setApplicantId(applicationDto.getApplicantId());
        applicationInformation.setEducation(applicationDto.getEducation());
        applicationInformation.setNumberYearsExperience(applicationDto.getNumberYearsExperience());
        applicationInformation.setApplicationDate(applicationDto.getApplicationDate().toString());
        applicationInformation.setWorkingExperience(applicationDto.getWorkingExperience());
        applicationInformation.setPostingId(applicationDto.getPostingId());
        return applicationInformation;
    }
}
