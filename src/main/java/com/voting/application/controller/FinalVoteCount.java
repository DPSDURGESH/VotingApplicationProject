package com.voting.application.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.voting.application.service.VoteServices;

@RestController
public class FinalVoteCount {

    
    
    @Autowired
    VoteServices voteServices;
    
    
//    @GetMapping("/votecounts")
//    public String showVoteCount() {
//        // No need to add attributes here unless you want to pre-populate the form
//        return "votecounts"; // Name of your Thymeleaf template
//    }
    
    
    @GetMapping("/votecounts")
    public ResponseEntity<Map<String, Integer>> getCandidateVotes() {
        Map<String, Integer> votes = voteServices.getVoteCounts();
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
    
}
