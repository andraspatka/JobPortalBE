package com.jobportal.jobportal.service;

import com.jobportal.jobportal.component.CompanyComponent;
import com.jobportal.jobportal.dto.UserDto;
import com.jobportal.jobportal.exceptions.InvalidRoleException;
import com.jobportal.jobportal.exceptions.UserNotAddedException;
import com.jobportal.jobportal.model.Role;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.UserRepository;
import com.jobportal.jobportal.converter.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service that handles the functionality related to the Users.
 *
 * @since 13.03.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserRepository userRepository;
    private final CompanyComponent companyComponent;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Adds a user with its company in the database.
     *
     * @param userDto the current {@link UserDto} with the
     *                information of a user to be added
     * @throws UserNotAddedException if the email is not unique
     */
    public void addUser(UserDto userDto) {
        checkRole(userDto);
        if (isUniqueUser(userDto.getEmail())) {
                User user = UserConverter.convertDtoToEntity(userDto);
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
                companyComponent.addCompanyToUser(userDto.getCompany(), user);
            } else {
                throw new UserNotAddedException("Email for the introduced user already exists in te database.");
            }
    }

    private boolean isUniqueUser(String email) {
        return userRepository.findUserByEmail(email).isEmpty();
    }

    private void checkRole(UserDto userDto) {
        if(!userDto.getRole().equals(Role.EMPLOYEE)) {
            throw new InvalidRoleException("You cannot introduce a user with the role: " + userDto.getRole());
        }
    }
}
