package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.dto.DoctorSlotDto;
import com.prime.uc.dto.DoctorSlotsWithAvailabilityDto;
import com.prime.uc.model.Doctor;
import com.prime.uc.model.DoctorSlot;
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
	
	@GraphQLMutation(name = "deleteById")
	public String deleteBranchById(@GraphQLArgument(name = "id") UUID id) {
		doctorSlotRepo.deleteById(id);
         return "delete sucessful";
    }
	
	@GraphQLQuery(name = "getSlots")
    public List<DoctorSlot> getSlots(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")List<String> day){
    	return doctorSlotRepo.getSlots(day, branchId);
    }
	
	@GraphQLQuery(name = "getSlotsByBranch")
    public List<DoctorSlotDto> getSlotsByBranch(@GraphQLArgument(name = "branchId")UUID branchId){
    	return doctorSlotRepo.getSlotsByBranchId( branchId);
    }
	
	
	@GraphQLQuery(name = "getAllSlots")
    public List<DoctorSlot> getAllSlots(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")List<String> day){
    	return doctorSlotRepo.getAllSlots(day, branchId);
    }
	
	@GraphQLQuery(name = "getSlotsFor")
    public List<DoctorSlotsWithAvailabilityDto> getSlotsFor(@GraphQLArgument(name = "branchId")UUID branchId,@GraphQLArgument(name = "day")String day,@GraphQLArgument(name = "date")String date){
    	
		List<DoctorSlotDto> allSlots = doctorSlotRepo.getSlotsFor(branchId, day,date);
		
		
		List<DoctorSlotsWithAvailabilityDto> doctors = allSlots.stream().collect(Collectors.groupingBy((slot) -> slot.getDoctorId()==null?"Branch":slot.getDoctorId())).values().stream().map(doctorSlots ->{
			DoctorSlotsWithAvailabilityDto dto = new DoctorSlotsWithAvailabilityDto();
			doctorSlots.stream().findFirst().ifPresent(firstSlot -> {
				dto.setDoctorId(firstSlot.getDoctorId());
				dto.setDoctorName(firstSlot.getDoctorName());
				dto.setSpecializationName(firstSlot.getSpecializationName());
				List<DoctorSlotDto> availableSlots = new ArrayList<>(), unavailableSlots = new ArrayList<>();
				doctorSlots.stream().forEach(slot -> {
					if(slot.getIsAvailable()) {
						availableSlots.add(slot);
					}else {
						unavailableSlots.add(slot);
					}
				});
				
				dto.setAvailableSlots(availableSlots);
				dto.setUnavailableSlots(unavailableSlots);
			});

			return dto;
		}).collect(Collectors.toList());
		
	
		return doctors;
    }
}

