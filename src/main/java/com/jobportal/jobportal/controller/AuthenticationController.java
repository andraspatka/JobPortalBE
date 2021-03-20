package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.security.JwtRequest;
import com.jobportal.jobportal.security.JwtResponse;
import com.jobportal.jobportal.security.JwtTokenUtil;
import com.jobportal.jobportal.exceptions.InvalidCredentialsException;
import com.jobportal.jobportal.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for authentication a user with his email and password.
 *
 * @since 13.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    /**
     * Creates a token for a successful login and sends it to the client.
     *
     * @param authenticationRequest containing the email and password
     * @return response entity containing the generated token if the login
     * was successful or the error code 403 (FORBIDDEN) if the credentials were
     * invalid.
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws InvalidCredentialsException {
        final UserDetails userDetails = authenticationService
                .loadUserByUsername(authenticationRequest.getEmail());
        if (!userDetails.isAccountNonLocked()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User account is locked");
        }
        String token = jwtTokenUtil.generateToken(userDetails);
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * Authenticates a user with email and password.
     *
     * @param email    of the user
     * @param password of the user
     * @throws InvalidCredentialsException when the entered credentials are invalid
     */
    private void authenticate(String email, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("The added credentials are invalid!");
        }
    }
}