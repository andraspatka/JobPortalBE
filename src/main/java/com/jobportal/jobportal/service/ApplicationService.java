package com.jobportal.jobportal.service;

import com.jobportal.jobportal.converter.ApplicationConverter;
import com.jobportal.jobportal.dto.ApplicationsDto;
import com.jobportal.jobportal.model.Applications;
import com.jobportal.jobportal.repository.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ApplicationService {

    private final ApplicationRepository applicationRepository ;

    public void addApplication(ApplicationsDto applicationDto)
    {

        Applications applications = ApplicationConverter.convertApplicationDtoToEntity(applicationDto);
        applicationRepository.save(applications);

    }

    public void deleteApplication(ApplicationsDto applicationsDto)
    {
        Applications applications = ApplicationConverter.convertApplicationDtoToEntity(applicationsDto);
        applicationRepository.delete(applications);

    }

   /* public void updateApplication(ApplicationsDto applicationsDto)
    {
        Applications applications = ApplicationConverter.convertDtoToEntity(applicationsDto);
        applicationRepository.save(applications);
    }*/

    public Applications getApplication(ApplicationsDto applicationsDto)
    {
        Applications applications = ApplicationConverter.convertApplicationDtoToEntity(applicationsDto);
        return applicationRepository.getOne(applications.getId());

    }


}
