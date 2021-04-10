package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.RequestDto;
import com.jobportal.jobportal.model.Request;
import com.jobportal.openapi.model.EmployerRequest;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Converter class for transforming a
 * {@link Request} to a {@link RequestDto} and vice versa
 *
 * @since 09.04.2021
 * */
@UtilityClass
public class RequestConverter {

    public static Set<RequestDto> convertEntitiesToDtos(Set<Request> requests) {
        return requests.stream()
                .map(RequestConverter::convertEntityToDto)
                .collect(Collectors.toSet());
    }

    public static List<EmployerRequest> convertDtostoJsons(Set<RequestDto> requests) {
        return requests.stream()
                .map(RequestConverter::convertDtoToJson)
                .collect(Collectors.toList());
    }

    private static EmployerRequest convertDtoToJson(RequestDto request) {
        EmployerRequest employerRequest = new EmployerRequest();
        employerRequest.setId(request.getId());
        employerRequest.setRequestedByEmail(request.getRequestedByEmail());
        employerRequest.setRequestedByFirstName(request.getRequestedByFirstName());
        employerRequest.setRequestedByLastName(request.getRequestedByLastName());
        return employerRequest;
    }

    private static RequestDto convertEntityToDto(Request request) {
        Objects.requireNonNull(request.getRequestedBy());
        return RequestDto.builder()
                .id(request.getId())
                .requestedByFirstName(request.getRequestedBy().getFirstName())
                .requestedByLastName(request.getRequestedBy().getLastName())
                .requestedByEmail(request.getRequestedBy().getEmail())
                .build();
    }
}
