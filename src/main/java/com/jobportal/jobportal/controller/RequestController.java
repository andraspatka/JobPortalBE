package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.RequestException;
import com.jobportal.jobportal.service.RequestService;
import com.jobportal.openapi.api.RequestApi;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for the operations related to a Request.
 *
 * @since 28.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RequestController implements RequestApi {

    private static final String REQUEST_SENT_MESSAGE = "Request has been successfully sent to the admin.";

    private final RequestService requestService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> requestPost(@Valid String email) {
        try {
            requestService.sendRequestToBecomeAnEmployer(email);
            return ResponseEntity.ok(REQUEST_SENT_MESSAGE);
        } catch (CompanyNotExistingException | RequestException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }
}
