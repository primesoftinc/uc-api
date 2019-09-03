package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Transactional
	@Modifying
	@Query("update Branch b set b.branchName = ?1, b.code=?2,b.mobile =?3,"
			+ " b.landPhone = ?4 , b.email = ?5 , b.address = ?6 , b.contact = ?7 where b.id =?8")
	int update(String branchName, String code, String mobile, String landPhone, String email, String address,
			String contact, UUID id);

}
