package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.CompanyDto;
import com.jobportal.jobportal.model.Company;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Converter class for transforming a
 * {@link Company} to a {@link CompanyDto} and vice versa
 *
 * @since 09.04.2021
 */
@UtilityClass
public class CompanyConverter {

    public static List<CompanyDto> convertEntitiesToDtos(List<Company> companies) {
        return companies.stream()
                .map(CompanyConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private static CompanyDto convertEntityToDto(@NonNull Company company) {
        return CompanyDto.builder()
                .name(company.getName())
                .build();
    }
}
