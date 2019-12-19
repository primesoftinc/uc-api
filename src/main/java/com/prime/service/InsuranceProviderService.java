package com.prime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.BranchInsuranceProvider;
import com.prime.uc.model.InsuranceProvider;
import com.prime.uc.repo.InsuranceProviderRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class InsuranceProviderService {
	@Autowired
	private InsuranceProviderRepo insuranceProviderRepo ;
	
	@GraphQLMutation(name = "createInsuranceProvider")
    public InsuranceProvider saveBranchInsuranceProvider(@GraphQLArgument(name = "InsuranceProvider") InsuranceProvider insuranceProvider) {
        return insuranceProviderRepo.save(insuranceProvider);
    }
	@GraphQLQuery(name="getProviderName")
	public List<InsuranceProvider> getProviderName() {
		return insuranceProviderRepo.findAll();
	}

}
