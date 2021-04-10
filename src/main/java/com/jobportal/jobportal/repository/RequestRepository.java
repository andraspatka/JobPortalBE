package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Request;
import com.jobportal.jobportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 * Repository for the {@link Request} entity.
 *
 * @since 27.03.2021
 */
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> {

    Optional<Request> findByRequestedByAndApprovedBy(User requestBy, User approvedBy);

    Set<Request> findAllByApprovedBy(User approvedBy);
}
