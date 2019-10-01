package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Doctor;
import com.prime.uc.model.DoctorSlot;
import com.prime.uc.model.User;
import com.prime.uc.repo.DoctorSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorSlotService {
	
	@Autowired
	private DoctorSlotRepo doctorSlotRepo;
	
	@GraphQLMutation(name = "saveDoctorSlot")
    public List<DoctorSlot> saveDoctorSlot(@GraphQLArgument(name = "doctorSlots") List<DoctorSlot> doctorSlots) {
        return doctorSlotRepo.saveAll(doctorSlots);
	}
	
	@GraphQLQuery(name = "getSlot")
    public List<DoctorSlot> getDoctorSlotById(@GraphQLArgument(name = "id")UUID id){
    	return DoctorSlotRepo.getDoctorSlotById(id);
    }
	
	@GraphQLQuery(name = "getSlotsByDoctor")
    public List<Doctor> getSlotsByDoctor(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")String day){
    	return doctorSlotRepo.getDoctorSlotsById(branchId, day);
    }

	@GraphQLQuery(name = "getSlotsByDay")
    public List<Doctor> getSlotsByDay(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")List<String> days){
		List<Doctor> ds = new ArrayList<Doctor>();
//		for(int day = 0 ;day< days.size();day++) {
			 ds = doctorSlotRepo.getSlotsByDay(days.get(0), branchId);

//		}
		return ds;
    }
	
	@GraphQLMutation(name = "deleteBySlotTime")
	public String deleteBranchById(@GraphQLArgument(name = "slotTime") String slotTime) {
		doctorSlotRepo.deleteAllBySlotTime(slotTime);
         return "delete sucessful";
    }
	
	@GraphQLQuery(name = "getSlots")
    public List<DoctorSlot> getSlots(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")List<String> day){
    	return doctorSlotRepo.getSlots(day, branchId);
    }
}
