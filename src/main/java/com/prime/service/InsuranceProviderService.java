package com.prime.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public InsuranceProvider saveInsuranceProvider(@GraphQLArgument(name = "insuranceProvider") InsuranceProvider insuranceProvider) {
        return insuranceProviderRepo.save(insuranceProvider);
    }
	@GraphQLQuery(name="getProviders")
	public List<InsuranceProvider> getProviders() {
		return insuranceProviderRepo.findAll();
	}
	
	@GraphQLQuery(name="getProviderById")
	public Optional<InsuranceProvider> getProviderById(@GraphQLArgument(name = "providerId") UUID providerId){
		return insuranceProviderRepo.findById(providerId);
	}

}
