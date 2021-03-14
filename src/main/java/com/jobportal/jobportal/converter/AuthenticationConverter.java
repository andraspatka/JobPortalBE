package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.AuthenticationDto;
import com.jobportal.jobportal.model.User;
import lombok.experimental.UtilityClass;

/**
 * Converter class to create a {@link AuthenticationDto} from an {@link User}
 *
 * @since 13.03.2021
 */
@UtilityClass
public class AuthenticationConverter {

    /**
     * Converts a {@link User} to a {@link AuthenticationDto}
     *
     * @return dto with the authentication information
     */
    public static AuthenticationDto convertEntityToDto(User user) {
        return AuthenticationDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
