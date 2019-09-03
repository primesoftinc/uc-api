package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.Privilege;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, UUID>{

//	@Query("select privilege from Privilege privilege")
//	List<Privilege> retrive();
	
	@Query("select privilege from Privilege privilege")
	List<Privilege> findAll();

}
