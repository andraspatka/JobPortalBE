package com.jobportal.jobportal.repository;


import com.jobportal.jobportal.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository for the {@link Category} entity
 *
 * @since 03.04.2021
 */
@Transactional
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findCategoriesByName(String Name);
    Optional<Category> findById(Long id);
    void deleteById(Long id);
}
