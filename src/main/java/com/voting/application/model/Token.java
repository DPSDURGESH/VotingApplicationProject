package com.voting.application.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;
    
    @Column(name="is_valid")
	private boolean isValid;

	public Long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public void setExpiryDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public Date getExpiryDate() {
		// TODO Auto-generated method stub
		return null;
	}


    // Getters and setters
}
