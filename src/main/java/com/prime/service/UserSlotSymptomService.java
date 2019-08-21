package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.User;
import com.prime.uc.model.UserSlot;
import com.prime.uc.model.UserSlotSymptom;
import com.prime.uc.repo.UserSlotSymptomRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserSlotSymptomService {
	@Autowired
	private UserSlotSymptomRepo userSlotSymptomRepo;

	@GraphQLMutation(name = "saveUserSlotSymptom")
	public UserSlotSymptom saveUserSlotSymptom(@GraphQLArgument(name = "userSlot") UserSlotSymptom userSlotSymptom) {
		return userSlotSymptomRepo.save(userSlotSymptom);
	}

	@GraphQLQuery(name = "getSymptom")
	public List<UserSlot> getUserById(@GraphQLArgument(name = "id") UUID id) {
		return userSlotSymptomRepo.getUserSloySymptomById(id);
	}
}
