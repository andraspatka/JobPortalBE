package com.jobportal.jobportal.service;


import com.jobportal.jobportal.converter.ConvertPostingListToDtoList;
import com.jobportal.jobportal.converter.PostingsConverter;
import com.jobportal.jobportal.dto.PostingCompleteDto;
import com.jobportal.jobportal.dto.PostingDto;
import com.jobportal.jobportal.dto.PostingSimpleDto;
import com.jobportal.jobportal.exceptions.*;
import com.jobportal.jobportal.model.Category;
import com.jobportal.jobportal.model.Posting;
import com.jobportal.jobportal.model.User;
import com.jobportal.jobportal.repository.CategoryRepository;
import com.jobportal.jobportal.repository.PostingRepository;
import com.jobportal.jobportal.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service which contains functionalities for Posting
 *
 * @since 03.04.2021
 */
@Service
@Transactional
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PostingsService {

    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void addPosting(PostingDto postingDto) {
        Posting postings = PostingsConverter.convertPostingsDtoToEntity(postingDto);
        Category category = categoryRepository.getOne(postingDto.getCategory());
        User user = userRepository.getOne(postingDto.getPostedBy());
        postings.setPostedBy(user);
        postings.setCategory(category);
        if (postings.getDeadline().isBefore(postings.getPostedAt()))
            throw new PostingDeadlineBeforeException("Deadline before Posting Date");
        postingRepository.save(postings);
    }

    public void editPosting(PostingSimpleDto postingSimpleDto) {
        if (postingSimpleDto.getId() == null)
            throw new PostingNotEditedException("Posting could not be edited. Posting is null");

        Posting posting = postingRepository.getOne(postingSimpleDto.getId());

        posting.setName(postingSimpleDto.getName());
        posting.setDescription(postingSimpleDto.getDescription());
        posting.setRequirements(postingSimpleDto.getRequirements());
        if (posting.getDeadline().isBefore(posting.getPostedAt()))
            throw new PostingDeadlineBeforeException("Deadline before Posting Date");
        posting.setDeadline(postingSimpleDto.getDeadline());
        postingRepository.save(posting);
    }

    public void deletePosting(Long id) {
        Optional<Posting> postingOptional = postingExists(id);
        if (postingOptional.isPresent()) {
            Posting posting = postingOptional.get();
            posting.getPostedBy().getPostings().remove(posting);
            posting.getCategory().getPostings().remove(posting);
            posting.setPostedBy(null);
            posting.setCategory(null);
            postingRepository.delete(posting);
        } else {
            throw new PostingNotExistingException("Posting with given id does not exists");
        }
    }

    /**
     * Finds a posting after its is.
     *
     * @param id of the {@link Posting}
     * @return the found {@link Posting}
     * @throws PostingNotExistingException if no posting was found
     * */
    public PostingCompleteDto findPostingById(@NonNull Long id) {
        Optional<Posting> posting = postingRepository.findById(id);
        if (posting.isPresent()) {
            return PostingsConverter.convertPostingEntityToCompleteDto(posting.get());
        } else {
            throw new PostingNotExistingException("No posting with the given id was found.");
        }
    }

    private Optional<Posting> postingExists(long id) {
        return postingRepository.findById(id);
    }

    public List<PostingCompleteDto> getListOfPostings() {
        return ConvertPostingListToDtoList.toDtoList(postingRepository.findAll());
    }

    public PostingDto addNumberOfViewsByOne(Long id) {
        Posting posting = postingRepository.findById(id).get();
        posting.setNumberOfViews(posting.getNumberOfViews() + 1L);
        return PostingsConverter.convertPostingEntityToDto(posting);
    }
}
