package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.UserDto;
import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.InvalidRoleException;
import com.jobportal.jobportal.exceptions.UserNotAddedException;
import com.jobportal.jobportal.model.Role;
import com.jobportal.jobportal.service.UserService;
import com.jobportal.openapi.api.UsersApi;
import com.jobportal.openapi.model.AuthenticationResponse;
import com.jobportal.openapi.model.UserInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for the operations related to a User.
 *
 * @since 13.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController implements UsersApi {

    private static final String USER_ADDED_MESSAGE = "User was successfully added";
    private static final String USER_NOT_ADDED_MESSAGE = "User could not be added";

    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<AuthenticationResponse> usersPost(@Valid UserInformation userInformation) {
        final UserDto userDto = buildUserDto(userInformation);
        try {
            userService.addUser(userDto);
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(USER_ADDED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (UserNotAddedException | InvalidRoleException | CompanyNotExistingException exception) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(USER_NOT_ADDED_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }

    private UserDto buildUserDto(@Valid UserInformation userInformation) {
        return UserDto.builder()
                .email(userInformation.getEmail())
                .password(userInformation.getPassword())
                .firstName(userInformation.getFirstname())
                .lastName(userInformation.getLastname())
                .company(userInformation.getCompany())
                .role(Role.valueOf(userInformation.getRole()))
                .build();
    }
}
