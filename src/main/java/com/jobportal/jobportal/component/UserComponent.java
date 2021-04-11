package com.jobportal.jobportal.component;

import com.jobportal.jobportal.exceptions.UnknownUserException;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Handles the operations related to a {@link }
 *
 * @since 11.04.2021
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserComponent {

    private final UserRepository userRepository;

    /**
     * Finds a {@link User} with the given id.
     *
     * @param userId of the {@link User}
     * @return the found {@link User} with the given id
     * @throws UnknownUserException if no user with the
     *                              given Id was found
     */
    public User getUserById(@NonNull Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UnknownUserException("No user with the given Id was found.");
        }
    }
}
