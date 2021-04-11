package com.jobportal.jobportal.service;


import com.jobportal.jobportal.component.PostingComponent;
import com.jobportal.jobportal.component.UserComponent;
import com.jobportal.jobportal.converter.ApplicationConverter;
import com.jobportal.jobportal.dto.ApplicationDto;
import com.jobportal.jobportal.exceptions.ApplicationException;
import com.jobportal.jobportal.model.Application;
import com.jobportal.jobportal.model.Posting;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service which contains functionalities
 * for the Applications related to Jobs
 *
 * @since 11.04.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserComponent userComponent;
    private final PostingComponent postingComponent;

    /**
     * Save data of a job application for a user and a posting.
     *
     * @param applicationDto containing all the application's information
     */
    public void applyToAJob(@NonNull ApplicationDto applicationDto) {
        Application application = ApplicationConverter.convertDtoToEntity(applicationDto);
        application.addUser(userComponent.getUserById(applicationDto.getApplicantId()));
        application.addPosting(postingComponent.getPostingById(applicationDto.getPostingId()));
        applicationRepository.save(application);
    }

    /**
     * Deletes a job application for a user and a posting.
     *
     * @param applicationId of the current {@link Application}
     * @throws ApplicationException if no application with the given
     *                              id was found
     */
    public void unapplyFromJob(@NonNull Long applicationId) {
        Optional<Application> applicationOpt = applicationRepository.findById(applicationId);
        if (applicationOpt.isPresent()) {
            Application application = applicationOpt.get();
            application.removePosting(application.getPosting());
            application.removeUser(application.getUser());
            applicationRepository.delete(application);
        } else {
            throw new ApplicationException("No application with the given Id was found.");
        }
    }

    /**
     * et all applications with the given User Id.
     *
     * @param userId of the given {@link User}
     * @return list of application DTOs
     * */
    public List<ApplicationDto> getAllApplicationsForUser(@NonNull Long userId) {
        User user = userComponent.getUserById(userId);
        return ApplicationConverter.convertEntitiesToDtos(applicationRepository.findAllByUser(user));
    }

    /**
     * Get all applications with the given Posting Id.
     *
     * @param postingId of the given {@link Posting}
     * @return list of application DTOs
     * */
    public List<ApplicationDto> getAllApplicationsForPosting(@NonNull Long postingId) {
        Posting posting = postingComponent.getPostingById(postingId);
        return ApplicationConverter.convertEntitiesToDtos(applicationRepository.findAllByPosting(posting));
    }
}
