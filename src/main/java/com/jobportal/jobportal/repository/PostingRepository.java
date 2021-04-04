package com.jobportal.jobportal.repository;


import com.jobportal.jobportal.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Repository for the {@link Posting} entity
 *
 * @since 03.04.2021
 */
@Transactional
public interface PostingRepository extends JpaRepository<Posting,Long> {
    Optional<Posting> findById(Long id);
}
