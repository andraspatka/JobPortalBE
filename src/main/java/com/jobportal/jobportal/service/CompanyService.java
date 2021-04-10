package com.jobportal.jobportal.service;

import com.jobportal.jobportal.component.CompanyComponent;
import com.jobportal.jobportal.converter.CompanyConverter;
import com.jobportal.jobportal.dto.CompanyDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service which contains functionalities
 * for the Companies
 *
 * @since 03.04.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CompanyService {

    private final CompanyComponent companyComponent;

    /**
     * Gets all the available companies.
     *
     * @return list of company DTOs
     */
    public List<CompanyDto> getAllCompanies() {
        return CompanyConverter.convertEntitiesToDtos(companyComponent.loadCompanies());
    }
}
