package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dto.UserDto;
import com.jobportal.jobportal.exceptions.UserNotAddedException;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Adds a user in the database.
     *
     * @param userDto the current {@link UserDto} with the
     *                information of a user to be added
     * @throws UserNotAddedException if the email is not unique
     */
    public void addUser(UserDto userDto) {
        if (isUniqueUser(userDto.getEmail())) {
            User user = UserConverter.convertDtoToEntity(userDto);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new UserNotAddedException("Email for the introduced user already exists in te database.");
        }
    }

    private boolean isUniqueUser(String email) {
        return userRepository.findUserByEmail(email).isEmpty();
    }
}
