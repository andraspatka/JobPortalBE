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

import java.util.Objects;

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
     * @return added user
     * @throws UserNotAddedException if the user could not be inserted
     */
    public User addUser(UserDto userDto) {
        User user = UserConverter.convertDtoToEntity(userDto);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User addedUser = userRepository.save(user);
        if (Objects.nonNull(addedUser)) {
            return addedUser;
        }
        throw new UserNotAddedException("Error while trying to add the user: " + user);
    }
}