package com.voting.application.dto;

public class LoginResponseDTO {
	private boolean success;
	private String message;
	private String token;

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getToken() {
		return token;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
