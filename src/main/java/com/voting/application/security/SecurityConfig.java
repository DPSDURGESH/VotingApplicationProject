//package com.voting.application.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//	
//	 @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        return http
//	                .csrf(csrf -> csrf.disable()) // Disables CSRF protection
//	                .authorizeHttpRequests(auth -> auth.anyRequest()) // Requires authentication for all requests
//	                .formLogin(form -> form
//	                        .loginPage("/login.html") // Sets the custom login page
//	                        .loginProcessingUrl("/login") // Specifies the URL to process the login form
//	                        .defaultSuccessUrl("/voting", true) // Redirects to "/voting" upon successful login
//	                        .permitAll()) // Allows unauthenticated access to the login page
//	                .build(); // Builds the security filter chain
//	    }
//	
//}
