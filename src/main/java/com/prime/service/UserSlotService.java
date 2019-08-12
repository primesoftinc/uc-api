package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.UserSlot;
import com.prime.uc.repo.UserSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserSlotService {
	@Autowired
	private UserSlotRepo userSlotRepo;
	@GraphQLMutation(name = "saveUserRole")
    public UserSlot saveUserRole(@GraphQLArgument(name = "userSlot") UserSlot userSlot) {
        return userSlotRepo.save(userSlot);
    }
	

}
