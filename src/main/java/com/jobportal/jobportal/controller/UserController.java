package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.dto.UserDto;
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

    private final UserService userService;

    /**
     * {@inheritDoc}
     * */
    @Override
    public ResponseEntity<String> usersPost(@Valid UserInformation userInformation) {
        final UserDto userDto = UserDto.builder()
                .email(userInformation.getEmail())
                .password(userInformation.getPassword())
                .firstName(userInformation.getFirstname())
                .lastName(userInformation.getLastname())
                //TODO
//                .company(userInformation.getCompany())
                .role(Role.valueOf(userInformation.getRole()))
                .build();
        try {
            userService.addUser(userDto);
            return ResponseEntity.ok("User was successfully added");
        } catch (UserNotAddedException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A problem occurred while trying to add the user in the Job Portal.");
        }
    }
}
