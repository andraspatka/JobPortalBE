package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.converter.RequestConverter;
import com.jobportal.jobportal.dto.RequestDto;
import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.InvalidRoleException;
import com.jobportal.jobportal.exceptions.RequestException;
import com.jobportal.jobportal.exceptions.UnknownUserException;
import com.jobportal.jobportal.service.RequestService;
import com.jobportal.openapi.api.RequestApi;
import com.jobportal.openapi.model.AuthenticationResponse;
import com.jobportal.openapi.model.EmployerRequest;
import com.jobportal.openapi.model.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Controller for the operations related to a Request.
 *
 * @since 28.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class RequestController implements RequestApi {

    private static final String REQUEST_SENT_MESSAGE = "Request has been successfully sent to the admin.";
    private static final String REQUEST_UPDATED_MESSAGE = "Request has been successfully updated.";

    private final RequestService requestService;

    /**
     * {@inheritDoc}
     *
     * @param email of the admin
     */
    @Override
    public ResponseEntity<List<EmployerRequest>> requestEmailGet(String email) {
        try {
            Set<RequestDto> requests = requestService.getUnapprovedRequestsForAdmin(email);
            return ResponseEntity.ok(RequestConverter.convertDtostoJsons(requests));
        } catch (InvalidRoleException | UnknownUserException exception) {
            log.error(exception.getMessage());
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> requestEmailPost(String email) {
        try {
            AuthenticationResponse response = new AuthenticationResponse();
            requestService.sendRequestToBecomeAnEmployer(email);
            response.setBody(REQUEST_SENT_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (CompanyNotExistingException | RequestException exception) {
            log.error(exception.getMessage());
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(exception.getMessage());
            response.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok(response);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> requestPatch(@Valid RequestStatus requestStatus) {
        Objects.requireNonNull(requestStatus);
        try {
            AuthenticationResponse response = new AuthenticationResponse();
            requestService.updateRequestStatus(requestStatus.getId(), requestStatus.getStatus());
            response.setBody(REQUEST_UPDATED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (RequestException exception) {
            log.error(exception.getMessage());
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(exception.getMessage());
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        }

    }
}
