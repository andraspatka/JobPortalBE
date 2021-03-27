package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository for the {@link Company} entity.
 *
 * @since 27.03.2021
 */
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
