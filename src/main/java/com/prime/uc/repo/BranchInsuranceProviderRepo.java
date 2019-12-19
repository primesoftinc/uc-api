package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.BranchInsuranceProvider;

@Repository
public interface BranchInsuranceProviderRepo extends JpaRepository<BranchInsuranceProvider, UUID> {
	
	List<BranchInsuranceProvider> findAll();

	List<BranchInsuranceProvider> findAllByBranchId(UUID id);

	
	

}