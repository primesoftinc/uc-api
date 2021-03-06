package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Branch;
import com.prime.uc.model.BranchUser;
import com.prime.uc.model.UserSlot;
import com.prime.uc.repo.BranchRepo;
import com.prime.uc.repo.UserSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class BranchService {
	@Autowired
	private BranchRepo branchRepo;
	
	@Autowired
	private UserSlotRepo userSlotRepo;
	
	@GraphQLMutation(name = "saveBranch")
    public Branch saveBranch(@GraphQLArgument(name = "branch") Branch branch) {
        return branchRepo.save(branch);
    }
	
	@GraphQLQuery(name = "getBranch")
    public List<Branch>getBranch() {
        return branchRepo.retrive();
    }
	
	@GraphQLQuery(name = "getBranchById")
    public Branch getBranchById(@GraphQLArgument(name = "id") UUID id) {
        return branchRepo.getBranchById(id);
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
	
	@GraphQLQuery(name = "getAppointmentsByBranch")
    public List<UserSlot> getAppointments(@GraphQLArgument(name = "branchId") UUID branchId ,@GraphQLArgument(name = "date") String date) {
        return userSlotRepo.retriveAppointmentsByBranchId(branchId,date);
    }
	
	@GraphQLQuery(name = "getAppointmentsBetweenDate")
	public List<UserSlot> getAppointmentsBetweenDate(@GraphQLArgument(name = "fromDate") String fromDate,
			@GraphQLArgument(name = "toDate") String toDate ,@GraphQLArgument(name = "branchId") UUID branchId){
		return userSlotRepo.getAppointmentsBetweenDate(branchId,fromDate,toDate);
	}


}
