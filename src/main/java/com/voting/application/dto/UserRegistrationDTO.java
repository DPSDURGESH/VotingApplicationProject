package com.voting.application.dto;

public class UserRegistrationDTO {
	
	private long id;
	
	String username;

	private String password;
	
	private String email;
	
	
	

	
	public UserRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UserRegistrationDTO(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}



	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	


	


	public String getEmail() {
		return email;
	}


	


	public void setEmail(String email) {
		this.email = email;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}


	
	
}
