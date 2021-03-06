package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.security.JwtTokenUtil;
import com.jobportal.jobportal.service.AuthenticationService;
import com.jobportal.openapi.api.LoginApi;
import com.jobportal.openapi.model.AuthenticationResponse;
import com.jobportal.openapi.model.JwtRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for authentication a user with his email and password.
 *
 * @since 13.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationController implements LoginApi {

    private static final String USER_ACCOUNT_LOCKED_MESSAGE = "User account is locked";
    private static final String INVALID_CREDENTIALS_MESSAGE = "The entered credentials are invalid!";

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> loginPost(@Valid JwtRequest jwtRequest) {
        final UserDetails userDetails = authenticationService
                .loadUserByUsername(jwtRequest.getEmail());
        if (!userDetails.isAccountNonLocked()) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(USER_ACCOUNT_LOCKED_MESSAGE);
            response.setStatus(HttpStatus.FORBIDDEN);
            return ResponseEntity.ok(response);
        }
        String token = jwtTokenUtil.generateToken(userDetails);
        return authenticate(jwtRequest.getEmail(), jwtRequest.getPassword(), token);
    }


    /**
     * Authenticates a user with email and password.
     *
     * @param email    of the user
     * @param password of the user
     * @param token generated token for the user
     */
    private ResponseEntity<AuthenticationResponse> authenticate(String email, String password, String token) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(token);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(INVALID_CREDENTIALS_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }
}