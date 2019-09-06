package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserSlot;

@Repository
public interface UserSlotRepo  extends JpaRepository<UserSlot, UUID> {

	
	@Query("select us from UserSlot us join fetch us.branch where us.branch.id = ?1")
	UserSlot retriveUserSlotByUserBranchId(UUID id);
	

}
