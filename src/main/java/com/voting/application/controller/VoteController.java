package com.voting.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.voting.application.security.JwtService;
import com.voting.application.service.CandidateService;
import com.voting.application.service.VoteServices;
import com.voting.application.util.CommunicationUtil;

@RestController
@RequestMapping
public class VoteController {
	
	@Autowired
    private final VoteServices voteServices;
	
	@Autowired
    private  CommunicationUtil communicationUtil;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private CandidateService candidateService;
    
    
	
    public VoteController(VoteServices voteServices, CommunicationUtil communicationUtil, JwtService jwtService, CandidateService candidateService) {
		this.voteServices = voteServices;
		this.communicationUtil = communicationUtil;
		this.jwtService = jwtService;
		this.candidateService = candidateService;
		
	}


//	@GetMapping("/vote")
//    public String showVotingForm(Model model) {
//        // No need to add attributes here unless you want to pre-populate the form
//        return "vote"; // Name of your Thymeleaf template
//    }
    

    @PostMapping("/vote")
    public ResponseEntity<String> castVote(@RequestBody Map<String, Object> requestBody) {
    	
        Integer userIdInt = (Integer) requestBody.getOrDefault("id", null);
        String candidateName = (String) requestBody.getOrDefault("candidateName", null);

        if (userIdInt == null || candidateName == null) {
            return ResponseEntity.badRequest().body("Both 'id' and 'candidateName' are required.");
        }

        // Convert Integer to Long safely
        Long userId = userIdInt!= null? Long.valueOf(userIdInt) : null;

        try {
            
            voteServices.castVote(userId, candidateName);
            communicationUtil.sendEmail("psdp4653@gmail.com", "Vote Succecfull", "Thank You Vote for.");
            return ResponseEntity.ok("Vote cast successfully");
        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    
    @PostMapping("/votes")
    public ResponseEntity<String> castVote(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> requestBody) {
        try {
            // Extract userId from token
            String jwtToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            Long userId = jwtService.extractUserId(jwtToken);
            String candidateName = (String) requestBody.getOrDefault("candidateName", null);
           

            // Ensure userId and candidateName are provided
            if (userId == null || candidateName == null) {
                return ResponseEntity.badRequest().body("Both 'userId' and 'candidateName' are required.");
            }

            // Cast the vote
            voteServices.castVote(userId, candidateName);
            communicationUtil.sendEmail("psdp4653@gmail.com", "Vote Successful", "Thank you for voting.");

            return ResponseEntity.ok("Vote cast successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
