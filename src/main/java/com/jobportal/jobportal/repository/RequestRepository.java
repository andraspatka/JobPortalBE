package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for the {@link Request} entity.
 *
 * @since 27.03.2021
 */
@Transactional
public interface RequestRepository extends JpaRepository<Request, Long> {
}
