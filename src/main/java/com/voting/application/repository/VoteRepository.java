package com.voting.application.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voting.application.model.Candidate;
import com.voting.application.model.UserRegistration;
import com.voting.application.model.Vote;

// In VoteRepository.java
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
	void save(Candidate candidate);

	//Optional<Vote> findByUserRegistration(UserRegistration userRegistration);

	Optional<Vote> findByUserRegistration(UserRegistration userRegistration);


	Optional<Vote> findById(Long userId);

	//Optional<Vote> findById(Long id);
}
