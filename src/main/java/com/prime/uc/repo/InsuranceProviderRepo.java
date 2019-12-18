package com.prime.uc.repo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.InsuranceProvider;


@Repository
public interface InsuranceProviderRepo extends JpaRepository<InsuranceProvider, UUID> {
	
	List<InsuranceProvider> findAll();


	
	

}