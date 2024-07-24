package com.voting.application.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.application.model.Candidate;
import com.voting.application.model.UserRegistration;
import com.voting.application.model.Vote;
import com.voting.application.repository.CandidateRepository;
import com.voting.application.repository.UserRepository;
import com.voting.application.repository.VoteRepository;
import com.voting.application.security.JwtService;

import java.util.List;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class VoteServices{

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private VoteRepository voteRepository;
    
    @Autowired
    UserRegistration userRegistration;
    
    @Autowired
    Candidate candidate;

    public void castVote(Long userId, String candidateName) {
        // Check if the user has already voted
    	
   
       // Optional<Vote> existingV ote = Optional.of(voteRepository.findById( userId).get());
    	//Optional<Vote> existVote=voteRepository.findByUserRegistrationId(userId);
    	Optional<Vote> existVote=voteRepository.findById(userId);
    	

        if (existVote.isPresent()) {
            throw new IllegalStateException("User has already voted.");
        }
        
        // Find the candidate by Name
        Candidate candidate = candidateRepository.findByCandidateName(candidateName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid candidate Name"));
        

        // Update the vote count for the selected candidate
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        
       // voteRepository.count();

        // Save the updated candidate
       // voteRepository.save(candidate);
        
        
        UserRegistration userRegistration = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        

        // Create and save the new vote
        

        Vote newVote = new Vote();
        
        
        newVote.setUserRegistration(userRegistration);
        newVote.setCandidate(candidate); 
        newVote.setTotalVotes(newVote.getTotalVotes() + 1); // Directly incrementing the total votes by 1
        //newVote.setTotalVotes(1); // Assuming each vote increments the totalVote by 
        // Save the new vote
         voteRepository.save(newVote);
        
        
    }

	
    
    
    public Map<String, Integer> getVoteCounts() {
        List<Candidate> candidates = candidateRepository.findAll(); // Fetch all candidates
        return candidates.stream()
               .collect(Collectors.toMap(
                        Candidate::getCandidateName,
                        Candidate::getVoteCount)); // Collect vote counts
    }




//	public List<Vote> getCandidateVotes() {
//		// TODO Auto-generated method stub
//		List<Vote> finalResult = candidateRepository.findAll();
//		return finalResult;
//	}



//	public Integer intValue(String extractedToken) {
//	    
//	        // Attempt to parse the extracted token into an Integer
//	    	System.out.println("Mahadev");
//	        Integer userId = Integer.parseInt(jwtService.extractUserId(extractedToken));
//	        System.out.println("Durgesh ::"+userId);
//	        // Proceed with using userId if parsing was successful
//	        return userId;
//	}
	





//	public List<Vote> getCandidateVotes() {
//		
//		return null;
//	}  
    
}
