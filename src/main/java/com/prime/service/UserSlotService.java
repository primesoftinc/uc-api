package com.prime.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.UserSlot;
import com.prime.uc.repo.UserRepo;
import com.prime.uc.repo.UserSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserSlotService {
	@Autowired
	private UserSlotRepo userSlotRepo;
	@Autowired
	private UserRepo userRepo;
	
	
	@GraphQLMutation(name = "saveUserSlot")
    public UserSlot saveUserSlot(@GraphQLArgument(name = "userSlot") UserSlot userSlot) {
        return userSlotRepo.save(userSlot);
    }
	@GraphQLMutation(name = "updateUserSlot")
    public UserSlot updateUserSlot(@GraphQLArgument(name = "userSlot") UserSlot userSlot) {
        userRepo.save(userSlot.getUser());
         userSlot = userSlotRepo.save(userSlot);
        return userSlot;
    }
	
	@GraphQLMutation(name = "updateUserSlotNotes")
	public int updateUserSlotNotes(@GraphQLArgument(name = "notes") String notes ,
			@GraphQLArgument(name = "id") UUID id) {
	        return userSlotRepo.updateUserSlotNotes(notes,id);
	}
	@GraphQLMutation(name = "updateUserSlotStatus")
	public int updateUserSlotStatus(@GraphQLArgument(name = "status") String status ,
			@GraphQLArgument(name = "id") UUID id) {
		if(status.equals("cancelled")) {
			userSlotRepo.updateUserSlotCancell(id,true);
		}else {
			userSlotRepo.updateUserSlotCancell(id,false);
		}
		if(status.equals("attended")) {
			userSlotRepo.updateUserSlotattended(id,"yes");

		}else {
			userSlotRepo.updateUserSlotattended(id,"no");
		}
	        return userSlotRepo.updateUserSlotStatus(status,id);
	}
	
	@GraphQLQuery(name = "getUserSlotId")
    public UserSlot getBranchId(@GraphQLArgument(name="id")UUID id) {
        return userSlotRepo.retriveUserSlotByUserBranchId(id);
    }
	
	@GraphQLQuery(name = "getUserSlotById")
    public Optional<UserSlot> getUserSlotById(@GraphQLArgument(name="id")UUID id) {
        return userSlotRepo.findById(id);
    }
	
	
	@GraphQLQuery(name = "getAppointments")
    public List<UserSlot> getAppointments(@GraphQLArgument(name = "doctorId") UUID doctorId) {
        return userSlotRepo.getAppointmentsOrderByDate(doctorId);
    }
	
	@GraphQLQuery(name = "getUserSlotByUserId")
    public List<UserSlot> getUserSlotByUserId(@GraphQLArgument(name="userId")UUID userId) {
        return userSlotRepo.retrieveByUserId(userId);
    }
	
	

}
