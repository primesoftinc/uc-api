package com.prime.uc.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.prime.uc.model.BranchUser;
import com.prime.uc.model.User;
import com.prime.uc.model.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

	List<User> getUserById(UUID id);
	
	@Query("select u from User u where  u.name=?1 and u.password=?2")
	User getUserDetails(String name,String password);	
	
	
	@Query("select user from User user")
	List<User> retrive();
	
	
	@Query("select bu  from BranchUser bu join fetch bu.user u join fetch bu.branch b  where  b.id =?1 and u.isDeleted = ?2")
	List<BranchUser> getUsersBybranchID(UUID id,Boolean isDeleted);
	
	@Transactional
	@Modifying
	@Query("update User u set u.name = ?1, u.firstName=?2, u.lastName =?3,"
			+ " u.password = ?4 , u.email = ?5 , u.address = ?6 ,u.phone = ?7 where u.id =?8")
	int update(String name, String firstName, String lastName, String password, String email, String address,
			String phone, UUID id);
	
	
	@Query("select bu from BranchUser bu join fetch bu.user u join fetch bu.branch where u.name =?1 and u.password=?2")
	BranchUser getUserAndBranchDetails(String name, String password);
	
	@Transactional
	@Modifying
	@Query("update User u set u.isDeleted = 1  where u.id= ?1")
	void delete(UUID id);

	@Query("select ur from UserRole ur join fetch ur.user u join fetch ur.role r  where u.id = ?1")
	List<UserRole> getRolesByUserId(UUID id);

	@Query("select u.name from User u")
	List<String> getUserNames();

	@Transactional
	@Modifying
	@Query("update User u set u.address = ?2,u.status = 'ADDRESS_DONE' where u.id = ?1")
	int updateUserAddress(UUID userId,String address);

	
	
	
	
//	@Query("select u from User u join fetch u.doctor d where u.id=?1")
//	User getDoctorSpecializationsById(UUID id);
    

}
