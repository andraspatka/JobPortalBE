package com.jobportal.jobportal.component;

import com.jobportal.jobportal.model.Company;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.CompanyRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * Handles the operations related to a {@link Company}
 *
 * @since 27.03.2021
 * */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CompanyComponent {

    private final CompanyRepository companyRepository;

    /**
     * Adds a new company if the name of the company is not
     * already present.
     *
     * @param companyName name of the company
     * @param user        to be added to this company
     */
    public void addCompany(@NonNull String companyName, @NotNull User user) {
        Company existingCompany = getCompanyByName(companyName);
        if (Objects.isNull(existingCompany)) {
            Company newCompany = new Company();
            newCompany.setName(companyName);
            newCompany.addUser(user);
            companyRepository.save(newCompany);
        } else {
            existingCompany.addUser(user);
        }
    }

    /**
     * Checks if a company with this name exists
     *
     * @return company with the given name or null if no company
     * is found
     */
    private Company getCompanyByName(String companyName) {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .filter(company ->
                        company.getName().toUpperCase().equals(companyName.toUpperCase()))
                .findFirst().orElse(null);
    }


}
