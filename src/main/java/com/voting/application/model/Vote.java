package com.voting.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserRegistration userRegistration; // Assuming User is another entity class

    @ManyToOne
    @JoinColumn(name="candidat_id")
    private Candidate candidate; // Assuming Candidate is another entity class

    @Column(name = "total_votes")
    private int totalVotes;

    // Constructors, getters, and setters
    public Vote() {
    }

	

	public UserRegistration getUserRegistration() {
		return userRegistration;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	

	public void setUserRegistration(UserRegistration userRegistration) {
		this.userRegistration = userRegistration;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public void setTotalVotes(int totalVotes) {
		this.totalVotes = totalVotes;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

   

    

	


	
}
