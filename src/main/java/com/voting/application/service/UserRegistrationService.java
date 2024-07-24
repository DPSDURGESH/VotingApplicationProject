package com.voting.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.voting.application.dto.LoginRequestDTO;
import com.voting.application.model.UserRegistration;
import com.voting.application.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRegistrationService {

	@Autowired
	private final UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEnCoder;

	public UserRegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.passwordEnCoder = new BCryptPasswordEncoder();
	}

	public List<String> validate(UserRegistration userRegistration) {
		List<String> error = new ArrayList<>();

		if (userRegistration.getEmail() == null) {
			error.add("Email can not be empty");
		}

		if (userRegistration.getUsername() == null) {
			error.add("UserName can not be empty");
		}

		if (userRegistration.getPassword() == null) {
			error.add("Password can not be empty");
		}

		return error;

	}

	// Method to save a user
	public UserRegistration saveUser(UserRegistration userRegistration) {

		String hashcode = passwordEnCoder.encode(userRegistration.getPassword());
		userRegistration.setPassword(hashcode);

		return userRepository.save(userRegistration);
	}

	// Method to find a user by ID
	public Optional<UserRegistration> findUserById(Long id) {
		return userRepository.findById(id);
	}

	// Method to find all users
	public List<UserRegistration> findAllUsers() {
		return userRepository.findAll();
	}

	// Method to delete a user by ID
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public UserRegistration login(LoginRequestDTO loginRequestDTO) {
		

		Optional<UserRegistration> userRegistrationo = userRepository.findByUsername(loginRequestDTO.getUsername());
		
		UserRegistration userRegistration = null;

		if (userRegistrationo.isPresent()) {

			UserRegistration userRegistrationdb = userRegistrationo.get();

			if (passwordEnCoder.matches(loginRequestDTO.getPassword(), userRegistrationdb.getPassword())) {
				
				userRegistration = userRegistrationdb;
			}

			/*
			 * if (loginRequestDTO.getPassword().equals(userRegistrationdb.getPassword())) {
			 * userRegistration = userRegistrationdb; }
			 */

		}
		
		return userRegistration;
	}
	
	
	 public Page<UserRegistration> findAllUsers(Pageable pageable) {
	        return userRepository.findAll(pageable);
	    }

	    // Method to find users within a specific range
	    public Page<UserRegistration> findUsersByRange(int start, int end) {
	        Pageable pageable = PageRequest.of(start / (end - start), end - start);
	        return userRepository.findAll(pageable);
	    }

}
