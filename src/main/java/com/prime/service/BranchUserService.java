package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.BranchUser;
import com.prime.uc.repo.BranchUserRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi

public class BranchUserService {
	@Autowired
	private BranchUserRepo branchUserrepo;
	
	@GraphQLMutation(name = "saveBranchUser")
    public BranchUser saveBranchUser(@GraphQLArgument(name = "branchUser") BranchUser branchUser) {
        return branchUserrepo.save(branchUser);
    }
}
