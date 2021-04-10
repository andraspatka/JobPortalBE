package com.jobportal.jobportal.service;

import com.jobportal.jobportal.converter.RequestConverter;
import com.jobportal.jobportal.dto.RequestDto;
import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.InvalidRoleException;
import com.jobportal.jobportal.exceptions.RequestException;
import com.jobportal.jobportal.exceptions.UnknownUserException;
import com.jobportal.jobportal.model.Request;
import com.jobportal.jobportal.model.Role;
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
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Loads all the unapproved requests for an admin.
     *
     * @param email of the admin who's requests must be found
     * @return set containing all the unapproved requests for the admin
     * */
    public Set<RequestDto> getUnapprovedRequestsForAdmin(@NotNull String email) {
        Optional<User> admin = userRepository.findUserByEmail(email);
        if (admin.isPresent()) {
            if (Role.ADMIN.equals(admin.get().getRole())) {
                Set<Request> requests = requestRepository.findAllByApprovedBy(admin.get())
                        .stream()
                        .filter(request -> Objects.isNull(request.getApprovedOn()))
                        .collect(Collectors.toSet());
                return RequestConverter.convertEntitiesToDtos(requests);
            } else {
                throw new InvalidRoleException("The user with the given id is not an admin.");
            }
        } else {
            throw new UnknownUserException("Admin with the given does not exist.");
        }
    }

    private void sendRequest(@NonNull User sender) {
        switch (sender.getRole()) {
            case EMPLOYEE: {
                if (requestAlreadyExists(sender, sender.getCompany().getAdmin())) {
                    throw new RequestException("Request has already been sent.");
                } else {
                    requestRepository.save(buildRequest(sender));
                }
                break;
            }
            case ADMIN:
                throw new RequestException("The admin of this company cannot become an employer.");
            case EMPLOYER:
                throw new RequestException("The user is already an employer.");
        }
    }

    private Request buildRequest(@NonNull User sender) {
        return Request.builder()
                .approvedBy(sender.getCompany().getAdmin())
                .requestedBy(sender)
                .build();
    }

    private boolean requestAlreadyExists(User sender, User receiver) {
        return requestRepository.findByRequestedByAndApprovedBy(sender, receiver).isPresent();
    }
}
