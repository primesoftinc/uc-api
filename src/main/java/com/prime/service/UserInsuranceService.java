package com.prime.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.UserInsurance;
import com.prime.uc.repo.UserInsuranceRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserInsuranceService {
	
	@Autowired
	private UserInsuranceRepo userInsuranceRepo;

	@GraphQLMutation(name = "saveUserInsurance")
	public UserInsurance saveUserInsurance(@GraphQLArgument(name = "userInsurance") UserInsurance userInsurance) {
		return userInsuranceRepo.save(userInsurance);
	}
	
	@GraphQLQuery(name = "getUserInsuranceByUserId")
	public Optional<UserInsurance> getUserInsuranceByUserId(@GraphQLArgument(name = "userId") UUID userId) {
		return userInsuranceRepo.findByUserId(userId);
	}

}
