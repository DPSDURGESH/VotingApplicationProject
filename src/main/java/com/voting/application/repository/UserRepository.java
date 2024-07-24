package com.voting.application.repository;




import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.voting.application.model.UserRegistration;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserRegistration, Long >{

	Optional<UserRegistration> findByUsername(String username);

	

	//Optional<UserRegistration> findByUserId(String userId);

	//List<UserRegistration> findByAccountStatus(String string);

	 

	//Optional<UserRegistration> findById(Long id);

	//void deleteById(Long id);

}
