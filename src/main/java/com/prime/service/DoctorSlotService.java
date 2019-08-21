package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.DoctorSlot;
import com.prime.uc.model.User;
import com.prime.uc.repo.DoctoSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorSlotService {
	
	@Autowired
	private DoctoSlotRepo doctorSlotRepo;
	
	@GraphQLMutation(name = "doctorSlot")
    public DoctorSlot saveDoctorSlot(@GraphQLArgument(name = "doctorSlot") DoctorSlot doctorSlot) {
        return doctorSlotRepo.save(doctorSlot);
	}
	
	@GraphQLQuery(name = "getSlot")
    public List<DoctorSlot> getDoctorSlotById(@GraphQLArgument(name = "id")UUID id){
    	return DoctoSlotRepo.getUserById(id);
    }
    
}
