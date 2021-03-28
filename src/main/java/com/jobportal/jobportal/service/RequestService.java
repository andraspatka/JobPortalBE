package com.jobportal.jobportal.service;

import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.RequestException;
import com.jobportal.jobportal.model.Request;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.RequestRepository;
import com.jobportal.jobportal.repository.UserRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Handles the operations related to a {@link Request}
 *
 * @since 27.03.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


    /**
     * Sends a request for the user with the given email to the
     * admin of its company to become an employer.
     *
     * @param email of the user who wants to become an employer
     * @throws RequestException            if the user cannot become an employer
     * @throws CompanyNotExistingException if the user belongs to no company
     */
    public void sendRequestToBecomeAnEmployer(@NotNull String email) {
        Optional<User> employerOpt = userRepository.findUserByEmail(email);
        if (employerOpt.isPresent()) {
            User employer = employerOpt.get();
            if (Objects.nonNull(employer.getCompany())) {
                sendRequest(employer);
            } else {
                throw new CompanyNotExistingException("No company for this user was found.");
            }
        }
    }

    private void sendRequest(@NonNull User sender) {
        switch (sender.getRole()) {
            case EMPLOYEE:
                requestRepository.save(buildRequest(sender));
                break;
            case ADMIN:
                throw new RequestException("The admin of this company cannot become an employer");
            case EMPLOYER:
                throw new RequestException("The user is already an employer");
        }
    }

    private Request buildRequest(@NonNull User sender) {
        return Request.builder()
                .approvedBy(sender.getCompany().getAdmin())
                .requestedBy(sender)
                .build();
    }
}
