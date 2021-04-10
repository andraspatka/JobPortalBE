package com.jobportal.jobportal.converter;

import com.jobportal.jobportal.dto.PostingCompleteDto;
import com.jobportal.jobportal.dto.PostingDto;
import com.jobportal.jobportal.model.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.UtilityClass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto for transforming a list of Posting Object to list of PostingDto objects
 *
 * @since 04.04.2021
 */
@UtilityClass
public class ConvertPostingListToDtoList implements Serializable {

    public static List<PostingCompleteDto> toDtoList(List<Posting> all) {
        if (all == null) {
            return null;
        }
        List<PostingCompleteDto> list = new ArrayList<>(all.size());
        for (Posting postings : all) {
            list.add(PostingsConverter.convertPostingEntityToCompleteDto(postings));
        }

        return list;
    }
}
