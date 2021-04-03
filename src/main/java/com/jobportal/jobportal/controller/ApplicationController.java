package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.converter.ApplicationConverter;
import com.jobportal.jobportal.dto.ApplicationsDto;
import com.jobportal.jobportal.model.Postings;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.ApplicationRepository;
import com.jobportal.jobportal.service.ApplicationService;
import com.jobportal.openapi.api.ApplicationsApi;
import com.jobportal.openapi.model.ApplicationsInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ApplicationController implements ApplicationsApi {

    private static final String APPLICATION_ADDED = "Application successfully added";
    private ApplicationService applicationService ;



    @Override
    public ResponseEntity<String> applicationsPost(@Valid ApplicationsInformation applicationsInformation) {

        final ApplicationsDto applicationsDto = ApplicationsDto.builder()
                .user((User) applicationsInformation.getUser())
                .posting((Postings) applicationsInformation.getPost())
                .education(applicationsInformation.getEducation())
                .experience(applicationsInformation.getExperience())
                .work_experience(applicationsInformation.getWorkExperience())
                .date_applied(Date.valueOf(LocalDate.now()))
                .build();
        try {
            applicationService.addApplication(applicationsDto);

        } catch (Exception e) {
            return ResponseEntity.ok("406");
        }
        return null;
    }
}
