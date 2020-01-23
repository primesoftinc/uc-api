package com.prime.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.HealthData;
import com.prime.uc.model.User;
import com.prime.uc.repo.HealthDataRepo;
import com.prime.uc.repo.UserRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class HealthDataService {
	
	@Autowired
	private HealthDataRepo healthDataRepo;
	@Autowired
	private UserRepo userRepo;
	
	 @GraphQLMutation(name = "savehealthData")
	    public HealthData savehealthData(@GraphQLArgument(name = "healthData") HealthData healthData) {
		 User u = healthData.getUser();
		 u.setStatus("DONE");
		 userRepo.save(u);
	        return healthDataRepo.save(healthData);
	    }
	 @GraphQLQuery(name = "getHealthData")
	 public HealthData getHealthData(@GraphQLArgument(name ="userId") UUID userId) {
		 
		 return healthDataRepo.getHealthDataByUserId(userId);
	 }

}
