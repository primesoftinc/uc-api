package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.HealthData;
import com.prime.uc.repo.HealthDataRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class HealthDataService {
	
	@Autowired
	private HealthDataRepo healthDataRepo;
	
	 @GraphQLMutation(name = "savehealthData")
	    public HealthData savehealthData(@GraphQLArgument(name = "healthData") HealthData healthData) {
	        return healthDataRepo.save(healthData);
	    }

}
