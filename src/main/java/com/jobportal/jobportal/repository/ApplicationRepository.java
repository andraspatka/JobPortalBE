package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ApplicationRepository extends JpaRepository<Applications , Long> {

}
