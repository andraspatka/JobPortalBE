package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository for the {@link User} entity.
 *
 * @since 13.03.2021
 */
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
