package com.voting.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voting.application.model.Token;
import com.voting.application.repository.TokenRepository;
import com.voting.application.repository.UserRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    // Generated Token and Save
    public String generateAndSaveToken() {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); // Set expiry date 1 hour from now
        tokenRepository.save(token);
        return tokenValue;
    }

    public boolean validateToken(String tokenValue) {
        // Retrieve the token from the database
        Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue);
  
        // Check if the token exists
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();

            // Convert expiryDate to LocalDateTime for easier comparison
            Date now = new Date();
            Date tokenExpiry = token.getExpiryDate();

            // Check if the token is expired
            if (now.after(tokenExpiry)) {
                // Token is expired
                return false;
            }

            // Token is valid
            return true;
        } else {
            // Token does not exist
            return false;
        }
    }

	public static boolean isValidToken(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
