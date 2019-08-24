package com.prime.uc.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

	List<User> getUserById(UUID id);
	
	@Query("select u from User u where  u.name=?1 and u.password=?2")
	User getUserDetails(String name,String password);
	
	
	

	@Query("select user from User user")
	List<User> retrive();
    

}
