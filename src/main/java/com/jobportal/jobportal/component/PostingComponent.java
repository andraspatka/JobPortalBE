package com.jobportal.jobportal.component;

import com.jobportal.jobportal.exceptions.PostingNotExistingException;
import com.jobportal.jobportal.model.Posting;
import com.jobportal.jobportal.repository.PostingRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Handles the operations related to a {@link }
 *
 * @since 11.04.2021
 */
@Component
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PostingComponent {

    private final PostingRepository postingRepository;

    /**
     * Finds a posting with the given id.
     *
     * @param postingId of the {@link Posting}
     * @throws PostingNotExistingException if no {@link Posting}
     *                                     with the given id was found
     */
    public Posting getPostingById(@NonNull Long postingId) {
        Optional<Posting> posting = postingRepository.findById(postingId);
        if (posting.isPresent()) {
            return posting.get();
        } else {
            throw new PostingNotExistingException("No posting with the given Id was found.");
        }
    }
}
