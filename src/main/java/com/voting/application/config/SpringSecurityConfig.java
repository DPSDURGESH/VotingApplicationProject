//package com.voting.application.config;
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//public class SpringSecurityConfig {
//	
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
//		return security.csrf(csrf -> csrf.disable())
//				.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//				.formLogin(login -> login.loginPage("/login.html")
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/voting")
//				.permitAll())
//			.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")))
//			.build();
//	}
//	
//	
//	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.builder()
//				.username("durgesh")
//				.password(passwordEncoder().encode("ITC@2022p"))
//				.roles("USER")
//				.build();
//		return new InMemoryUserDetailsManager(user);
//	}
//}
