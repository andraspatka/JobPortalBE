package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.PostingCompleteDto;
import com.jobportal.jobportal.dto.PostingDto;
import com.jobportal.openapi.model.PostingsInformationComplete;
import lombok.experimental.UtilityClass;

/**
 * Conversion class for transforming a {@link PostingDto} to
 * OpenApi PostingInformation
 *
 * @since 04.04.2021
 */
@UtilityClass
public class DtoToPostingInformation {

    public static PostingsInformationComplete convertPostingsDtoToPostingInformationOpenApi(PostingCompleteDto postingDto) {
        PostingsInformationComplete postingsInformation = new PostingsInformationComplete();
        postingsInformation.setId(postingDto.getId());
        postingsInformation.setPostedById(postingDto.getPostedBy());
        postingsInformation.setPostedAt(postingDto.getPostedAt());
        postingsInformation.setDeadline(postingDto.getDeadline());
        postingsInformation.setNumberOfViews(postingDto.getNumberOfViews());
        postingsInformation.setName(postingDto.getName());
        postingsInformation.setDescription(postingDto.getDescription());
        postingsInformation.setCategoryId(postingDto.getCategory());
        postingsInformation.setRequirements(postingDto.getRequirements());
        return postingsInformation;
    }
}
