package com.prime.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Branch;
import com.prime.uc.model.BranchUser;
import com.prime.uc.repo.BranchRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
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
	
	@GraphQLQuery(name = "getBranch")
    public List<Branch>getBranch() {
        return branchRepo.retrive();
    }
	
	@GraphQLMutation(name = "deleteBranch")
    public String deleteBranchById(@GraphQLArgument(name = "id") UUID id) {
         branchRepo.deleteById(id);
         return "delete sucessful";
    }
	
	

	@GraphQLQuery(name = "getBranchId")
    public BranchUser getBranchId(@GraphQLArgument(name="id")UUID id) {
        return branchRepo.retriveBranchByUserId(id);
    }
	
	
	@GraphQLMutation(name = "updateBranch")
    public Branch updateBranch(@GraphQLArgument(name = "branch") Branch branch) {
        return branchRepo.save(branch);
    }
	


}
