package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, UUID> {
	
	List<Role> findAll();

	@Query("select r from Role r")
	List<Role> retrive();

}
