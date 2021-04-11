package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.ApplicationDto;
import com.jobportal.jobportal.model.Application;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converter class to create an {@link Application} from
 * an {@link ApplicationDto} and vice versa
 *
 * @since 11.04.2021
 */
@UtilityClass
public class ApplicationConverter {

    public static Application convertDtoToEntity(@NonNull ApplicationDto applicationDto) {
        return Application.builder()
                .dateApplied(applicationDto.getApplicationDate())
                .education(applicationDto.getEducation())
                .experience(applicationDto.getNumberYearsExperience())
                .workExperience(applicationDto.getWorkingExperience())
                .build();
    }

    public static List<ApplicationDto> convertEntitiesToDtos(Set<Application> applications) {
        return applications.stream()
                .map(ApplicationConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private static ApplicationDto convertEntityToDto(@NonNull Application application) {
        return ApplicationDto.builder()
                .applicantId(application.getUser().getId())
                .applicationDate(application.getDateApplied())
                .education(application.getEducation())
                .numberYearsExperience(application.getExperience())
                .workingExperience(application.getWorkExperience())
                .postingId(application.getPosting().getId())
                .build();
    }

}
