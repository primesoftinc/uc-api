package com.prime.uc.repo;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.prime.uc.model.BranchUser;

public interface BranchUserRepo extends JpaRepository<BranchUser, UUID>{

	
	@Query("select bu from BranchUser bu join fetch bu.user u join fetch bu.branch b where u.id=?1")
	BranchUser getBranchUserById(UUID id);

	@Transactional
	@Modifying
	@Query( "update BranchUser bu  set bu.isDeleted = true  where bu.id =?1")
	int updateBranchUserIsDeleted(UUID userId);

}
