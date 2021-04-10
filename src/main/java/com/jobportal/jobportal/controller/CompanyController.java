package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.service.CompanyService;
import com.jobportal.openapi.api.CompaniesApi;
import com.jobportal.openapi.model.CompanyName;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Companies.
 *
 * @since 28.03.2021
 */
@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CompanyController implements CompaniesApi {

    private final CompanyService companyService;

    /**
     * {@inheritDoc}
     * */
    @Override
    public ResponseEntity<List<CompanyName>> companiesGet() {
        List<CompanyName> companyNames = new ArrayList<>();
        companyService.getAllCompanies().forEach(company -> {
            CompanyName companyName = new CompanyName();
            companyName.setName(company.getName());
            companyNames.add(companyName);

        });
        return ResponseEntity.ok(companyNames);
    }
}
