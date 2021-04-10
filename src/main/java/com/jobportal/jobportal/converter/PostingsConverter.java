package com.jobportal.jobportal.converter;


import com.jobportal.jobportal.dto.PostingCompleteDto;
import com.jobportal.jobportal.dto.PostingDto;
import com.jobportal.jobportal.model.Posting;
import lombok.experimental.UtilityClass;

/**
 * Converter class for transforming a
 * {@link Posting} to a {@link PostingDto} and vice versa
 * @since 03.04.2021
 */
@UtilityClass
public class PostingsConverter {

    public static Posting convertPostingsDtoToEntity(PostingDto postingDto){

        return Posting.builder()
                .postedAt(postingDto.getPostedAt())
                .deadline(postingDto.getDeadline())
                .numberOfViews(postingDto.getNumberOfViews())
                .name(postingDto.getName())
                .description(postingDto.getDescription())
                .requirements(postingDto.getRequirements())
                .build();
    }

    public static PostingDto convertPostingEntityToDto(Posting posting) {

        return PostingDto.builder()
                .postedBy(posting.getPostedBy().getId())
                .postedAt(posting.getPostedAt())
                .deadline(posting.getDeadline())
                .numberOfViews(posting.getNumberOfViews())
                .name(posting.getName())
                .description(posting.getDescription())
                .category(posting.getCategory().getId())
                .requirements(posting.getRequirements())
                .build();
    }

    public static PostingCompleteDto convertPostingEntityToCompleteDto(Posting posting){
        return PostingCompleteDto.builder()
                .id(posting.getId())
                .postedBy(posting.getPostedBy().getId())
                .postedAt(posting.getPostedAt())
                .deadline(posting.getDeadline())
                .numberOfViews(posting.getNumberOfViews())
                .name(posting.getName())
                .description(posting.getDescription())
                .category(posting.getCategory().getId())
                .requirements(posting.getRequirements())
                .build();
    }
}
