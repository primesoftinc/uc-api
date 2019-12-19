package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.BranchInsuranceProvider;
import com.prime.uc.repo.BranchInsuranceProviderRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BranchInsuranceProviderService {

	@Autowired
	private BranchInsuranceProviderRepo branchInsuranceProvideRepo;
	
	
	@GraphQLMutation(name = "createBranchInsuranceProvider")
    public BranchInsuranceProvider saveBranchInsuranceProvider(@GraphQLArgument(name = "branchInsuranceProvider") BranchInsuranceProvider branchInsuranceProvider) {
        return branchInsuranceProvideRepo.save(branchInsuranceProvider);
    }
}
