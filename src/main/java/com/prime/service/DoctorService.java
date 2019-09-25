package com.prime.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Doctor;
import com.prime.uc.model.DoctorSlot;
import com.prime.uc.repo.DoctorRepo;
import com.prime.uc.repo.DoctorSlotRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private DoctorSlotRepo doctorSlotRepo;
	
	@GraphQLMutation(name = "saveDoctor")
    public Doctor saveDoctor(@GraphQLArgument(name = "doctor") Doctor doctor) {
		UUID id = doctor.getId();
		List<DoctorSlot>  ds = doctor.getDoctorSlot();
		ds.stream().map(d -> {
			d.setDoctor(doctor);;
			return ds;
		}).collect(Collectors.toList());
		doctorSlotRepo.saveAll(ds);
        return doctorRepo.save(doctor);
    }
	
	@GraphQLQuery(name = "getDoctorDetails")
    public List<Doctor> getDoctorById(@GraphQLArgument(name = "id")UUID id){
    	return doctorRepo.getDoctorById(id);
    }
	
	@GraphQLQuery(name = "getDoctorsByBranch")
    public List<Doctor> getDoctorsByBranch(@GraphQLArgument(name = "id")UUID id){
		return doctorRepo.getDoctorsByBranch(id);
    }
	
	@GraphQLMutation(name = "deleteDoctor")
    public String deleteBranchById(@GraphQLArgument(name = "id") UUID id) {
		doctorRepo.deleteById(id);
         return "delete sucessful";
    }
}
