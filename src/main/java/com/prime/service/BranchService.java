package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Branch;
import com.prime.uc.model.User;
import com.prime.uc.repo.BranchRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BranchService {
	@Autowired
	private BranchRepo branchRepo;
	
	@GraphQLMutation(name = "saveBranch")
    public Branch saveBranch(@GraphQLArgument(name = "branch") Branch branch) {
        return branchRepo.save(branch);
    }

}