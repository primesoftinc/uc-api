package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.prime.uc.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {
	
	@Query("select  ur from UserRole ur join fetch ur.user u join fetch ur.role r join fetch r.rolePrivileges rp where u.id = ?1")
	List<UserRole> getUserRolesByUser(UUID Id);

	
	@Transactional
	@Modifying
	@Query("update UserRole ur set ur.isDeleted = 1  where ur.id= ?1")
	void updateRoleIsDeleted(UUID urId);

	


	
}
