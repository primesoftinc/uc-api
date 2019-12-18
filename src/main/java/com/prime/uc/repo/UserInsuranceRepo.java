package com.prime.uc.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserInsurance;

@Repository
public interface UserInsuranceRepo extends JpaRepository<UserInsurance, UUID> {
	
	List<UserInsurance> findAll();

	Optional<UserInsurance> findByUserId(UUID userId);
	
	

}