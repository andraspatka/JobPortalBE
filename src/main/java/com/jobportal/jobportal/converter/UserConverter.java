package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.UserDto;
import com.jobportal.jobportal.model.User;
import lombok.experimental.UtilityClass;

/**
 * Converter class to create a {@link User} from an {@link UserDto}
 * and vice versa.
 *
 * @since 13.03.2021
 */
@UtilityClass
public class UserConverter {

    /**
     * Converts a {@link UserDto} to a {@link User}
     *
     * @return converted User
     */
    public static User convertDtoToEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .password(userDto.getPassword())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }

    public static UserDto convertUserEntityToDto(User user){
        return UserDto.builder()
                .firstName(user.getFirstName())
                .password(user.getPassword())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
