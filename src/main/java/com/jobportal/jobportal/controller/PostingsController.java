package com.jobportal.jobportal.controller;

import com.jobportal.jobportal.converter.DtoToPostingInformation;
import com.jobportal.jobportal.dto.PostingCompleteDto;
import com.jobportal.jobportal.dto.PostingDto;
import com.jobportal.jobportal.dto.PostingSimpleDto;
import com.jobportal.jobportal.exceptions.CategoryNotExistingException;
import com.jobportal.jobportal.exceptions.PostingAlreadyExistsException;
import com.jobportal.jobportal.exceptions.PostingNotAddedException;
import com.jobportal.jobportal.exceptions.PostingNotExistingException;
import com.jobportal.jobportal.service.PostingsService;
import com.jobportal.openapi.api.PostingsApi;
import com.jobportal.openapi.model.AuthenticationResponse;
import com.jobportal.openapi.model.PostingInformationForUpdate;
import com.jobportal.openapi.model.PostingsInformation;
import com.jobportal.openapi.model.PostingsInformationComplete;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller CRUD Operations for Postings
 *
 * @since 03.04.2021
 */

@RestController
@CrossOrigin
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PostingsController implements PostingsApi {

    private static final String POSTING_ADDED_MESSAGE = "Posting was successfully added";
    private static final String POSTING_NOT_ADDED_MESSAGE = "Posting could not be added";
    private static final String POSTING_EDITED_MESSAGE = "Posting was successfully edited";
    private static final String POSTING_NOT_EDITED_MESSAGE = "Posting could not be edited";
    private static final String POSTING_DELETED_MESSAGE = "Posting was successfully deleted";
    private static final String POSTING_NOT_DELETED_MESSAGE = "Posting could not be deleted";
    private static final String POSTINGS_FETCHED_MESSAGE = "Posting list returned successfully";
    private static final String POSTINGS_NOT_FETCHED_MESSAGE = "Posting list not returned";
    private final PostingsService postingsService;


    /**
     * @return ResponseEntity<List < PostingsInformation>>
     */
    @Override
    public ResponseEntity<List<PostingsInformationComplete>> postingsGet() {
        List<PostingCompleteDto> list = postingsService.getListOfPostings();
        List<PostingsInformationComplete> result = new ArrayList<>();
        list.forEach(posting -> {
            PostingsInformationComplete postingsInformation = DtoToPostingInformation.convertPostingsDtoToPostingInformationOpenApi(posting);
            result.add(postingsInformation);

        });
        return ResponseEntity.ok(result);
    }

    /**
     * @param id (required)
     * @return ResponseEntity<AuthenticationResponse>
     */
    @Override
    public ResponseEntity<AuthenticationResponse> postingsIdDelete(Long id) {

        try {
            AuthenticationResponse response = new AuthenticationResponse();
            postingsService.deletePosting(id);
            response.setBody(POSTING_DELETED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (PostingNotExistingException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(POSTING_NOT_DELETED_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }

    /**
     * @param postingInformationForUpdate (optional)
     * @return ResponseEntity<AuthenticationResponse>
     */
    @Override
    public ResponseEntity<AuthenticationResponse> postingsPatch(@Valid PostingInformationForUpdate postingInformationForUpdate) {

        final PostingSimpleDto postingDto = PostingSimpleDto.builder()
                .id(postingInformationForUpdate.getId())
                .deadline(postingInformationForUpdate.getDeadline())
                .name(postingInformationForUpdate.getName())
                .description(postingInformationForUpdate.getDescription())
                .requirements(postingInformationForUpdate.getRequirements())
                .build();
        try {
            AuthenticationResponse response = new AuthenticationResponse();
            postingsService.editPosting(postingDto);
            response.setBody(POSTING_EDITED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (PostingNotExistingException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(POSTING_NOT_EDITED_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }

    /**
     * @param postingsInformation (optional)
     * @return ResponseEntity<AuthenticationResponse>
     */
    @Override
    public ResponseEntity<AuthenticationResponse> postingsPost(@Valid PostingsInformation postingsInformation) {

        final PostingDto postingDto = PostingDto.builder()
                .postedBy(postingsInformation.getPostedById())
                .postedAt(postingsInformation.getPostedAt())
                .deadline(postingsInformation.getDeadline())
                .numberOfViews(postingsInformation.getNumberOfViews())
                .name(postingsInformation.getName())
                .description(postingsInformation.getDescription())
                .category(postingsInformation.getCategoryId())
                .requirements(postingsInformation.getRequirements())
                .build();
        try {
            AuthenticationResponse response = new AuthenticationResponse();
            postingsService.addPosting(postingDto);
            response.setBody(POSTING_ADDED_MESSAGE);
            response.setStatus(HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (PostingNotAddedException | PostingAlreadyExistsException
                | PostingNotExistingException | CategoryNotExistingException e) {
            AuthenticationResponse response = new AuthenticationResponse();
            response.setBody(POSTING_NOT_ADDED_MESSAGE);
            response.setStatus(HttpStatus.UNAUTHORIZED);
            return ResponseEntity.ok(response);
        }
    }
}
