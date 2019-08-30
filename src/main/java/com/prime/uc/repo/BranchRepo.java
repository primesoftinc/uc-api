package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.Branch;
import com.prime.uc.model.BranchUser;
import com.prime.uc.model.User;

@Repository
public interface BranchRepo extends JpaRepository<Branch, UUID> {

	Branch getUserByid(UUID id);


	List<Branch> getBranchById(UUID id);
	
	@Query("select branch from Branch branch")
	List<Branch> retrive();
	
	@Query("select bu from BranchUser bu join fetch bu.branch where bu.user.id = ?1")
	 BranchUser retriveBranchByUserId(UUID id);

}
