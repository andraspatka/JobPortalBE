package com.jobportal.jobportal.service;

import com.jobportal.jobportal.dto.AuthenticationDto;
import com.jobportal.jobportal.exceptions.InvalidCredentialsException;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.UserRepository;
import com.jobportal.jobportal.converter.AuthenticationConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service logic for authenticating a User with his email and password.
 *
 * @since 13.03.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private AuthenticationManager authenticationManager;


    /**
     * Searches for an user after its email.
     *
     * @param email of the current {@link User}
     * @return dto containing the login information of an user;
     * null if user was not found
     */
    public AuthenticationDto getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).map(AuthenticationConverter::convertEntityToDto).orElse(null);
    }

    /**
     * Construct the {@link org.springframework.security.core.userdetails.User}
     * with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param email of the current {@link User}
     * @return dto containing the login information of an user
     * @throws UsernameNotFoundException if no user exists with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    true, true, true, true, AuthorityUtils.NO_AUTHORITIES);
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    /**
     * Authenticates a user with email and password.
     *
     * @param email    of the user
     * @param password of the user
     * @throws InvalidCredentialsException when the entered credentials are invalid
     */
    public void authenticate(String email, String password) throws InvalidCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("The added credentials are invalid!");
        }
    }
}
