package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Privilege;
import com.prime.uc.repo.PrivilegeRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class PrivilegeService {
	
	@Autowired
	private PrivilegeRepo privilegeRepo;
	
	@GraphQLMutation(name = "savePrivilege")
    public Privilege savePrivilege(@GraphQLArgument(name = "privilege") Privilege privilege) {
        return privilegeRepo.save(privilege);
    }
}
