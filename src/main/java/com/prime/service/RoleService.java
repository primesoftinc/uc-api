package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Role;
import com.prime.uc.repo.RoleRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
@Service
@GraphQLApi
public class RoleService {
	@Autowired
	private RoleRepo RoleRepo;
	
	
	 @GraphQLMutation(name = "saveRole")
	    public Role saveRole(@GraphQLArgument(name = "role") Role role) {
	        return RoleRepo.save(role);
	    }
}
