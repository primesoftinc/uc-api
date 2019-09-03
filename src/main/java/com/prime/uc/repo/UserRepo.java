package com.prime.uc.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	
	@Transactional
	@Modifying
	@Query("update User u set u.name = ?1, u.firstName=?2, u.lastName =?3,"
			+ " u.password = ?4 , u.email = ?5 , u.address = ?6 ,u.phone = ?7 where u.id =?8")
	int update(String name, String firstName, String lastName, String password, String email, String address,
			String phone, UUID id);
    

}
