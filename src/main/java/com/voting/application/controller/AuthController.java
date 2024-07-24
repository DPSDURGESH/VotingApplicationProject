package com.voting.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voting.application.dto.LoginRequestDTO;
import com.voting.application.dto.LoginResponseDTO;
import com.voting.application.dto.UserRegistrationDTO;
import com.voting.application.model.UserRegistration;
import com.voting.application.security.JwtService;
import com.voting.application.service.TokenService;
import com.voting.application.service.UserRegistrationService;

@RestController
@RequestMapping("/auth/api/users")
public class AuthController{
	@Autowired
	UserRegistrationService userRegistrationService;
	@Autowired
	TokenService tokenService;
	
	@Autowired
	JwtService jwtService;

	
//	 @GetMapping("/login")
//	    public String showLoginForm(Model model) {
//	        // No need to add attributes here unless you want to pre-populate the form
//	        return "login"; // Name of your Thymeleaf template
//	    }
	

	@PostMapping("/login")
	//@ResponseBody
	public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
		

		LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

		// Logic flow call From Service class


		UserRegistration userRegistration = userRegistrationService.login(loginRequestDTO);
		
		

		if (userRegistration == null) {
			
			loginResponseDTO.setSuccess(false);
			
			loginResponseDTO.setMessage("User Registration are not correct");
			
			return loginResponseDTO;
		}
	

		// Generate token
		String token1 = jwtService.generateToken(userRegistration);
		
		
		// Check Token Validation
		if (tokenService.validateToken(token1)) {
		    System.out.println("Token is valid");
		    // Proceed with login logic or any other operations
		    loginResponseDTO.setSuccess(true);
		    loginResponseDTO.setMessage("Login Successfully With validate");
		    // Return token if needed in response
		    loginResponseDTO.setToken(token1);
		} else {
		    System.out.println("Token is invalid or expired");
		    loginResponseDTO.setSuccess(false);
		    loginResponseDTO.setMessage("Token is invalid or expired");
		}

		

		// Response Preparation
		UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
		userRegistrationDTO.setEmail(userRegistration.getEmail());
		userRegistrationDTO.setId(userRegistration.getId());
		userRegistrationDTO.setUsername(userRegistration.getUsername());
		userRegistrationDTO.setPassword(userRegistration.getPassword());
	

		loginResponseDTO.setSuccess(true);
		loginResponseDTO.setMessage("Login Successfully");
		loginResponseDTO.setToken(token1);
	

		// loginResponseDTO.setUserRegistration(userRegistrationDTO);

		// Response preparation end

		// Response send
		return loginResponseDTO;


	}
}
