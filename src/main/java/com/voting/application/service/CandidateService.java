package com.voting.application.service;

import com.voting.application.model.Candidate;
import com.voting.application.model.Vote;
import com.voting.application.repository.CandidateRepository;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CandidateService {
	
	@Autowired
    private final CandidateRepository candidateRepository;

    
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    // Create a new candidate
    public Candidate createCandidate(@Valid Candidate candidate) {
        return candidateRepository.save(candidate);
    }

   

    // Delete a candidate by ID
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    // Retrieve a candidate by ID
    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    // Retrieve all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    
}
