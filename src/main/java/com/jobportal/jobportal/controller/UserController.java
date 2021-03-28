package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.UserDto;
import com.jobportal.jobportal.exceptions.CompanyNotExistingException;
import com.jobportal.jobportal.exceptions.InvalidRoleException;
import com.jobportal.jobportal.exceptions.UserNotAddedException;
import com.jobportal.jobportal.model.Role;
import com.jobportal.jobportal.service.UserService;
import com.jobportal.openapi.api.UsersApi;
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

    private final UserService userService;

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<String> usersPost(@Valid UserInformation userInformation) {
        final UserDto userDto = UserDto.builder()
                .email(userInformation.getEmail())
                .password(userInformation.getPassword())
                .firstName(userInformation.getFirstname())
                .lastName(userInformation.getLastname())
                .company(userInformation.getCompany())
                .role(Role.valueOf(userInformation.getRole()))
                .build();
        try {
            userService.addUser(userDto);
            return ResponseEntity.ok(USER_ADDED_MESSAGE);
        } catch (UserNotAddedException | InvalidRoleException | CompanyNotExistingException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }
}
