package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Application;
import com.jobportal.jobportal.model.Posting;
import com.jobportal.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Repository for the {@link Application} entity
 *
 * @since 11.04.2021
 */
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Set<Application> findAllByUser(User user);
    Set<Application> findAllByPosting(Posting posting);
}
