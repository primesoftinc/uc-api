package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prime.uc.model.UserRole;

public interface UserRoleRepo extends JpaRepository<UserRole, UUID> {
	
	@Query("select distinct ur from UserRole ur join fetch ur.user u join fetch ur.role r join fetch r.rolePrivileges rp where u.id = ?1")
	List<UserRole> getUserRolesByUser(UUID Id);

}
