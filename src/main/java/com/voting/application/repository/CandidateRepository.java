package com.voting.application.repository;

import com.voting.application.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing {@link Candidate} entities.
 */

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    /**
     * Finds a candidate by their name.
     *
     * @param candidateName the name of the candidate to find
     * @return an {@code Optional} describing the found candidate, if any
     */
    Optional<Candidate> findByCandidateName(String candidateName);
}
