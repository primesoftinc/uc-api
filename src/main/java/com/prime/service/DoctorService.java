package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Branch;
import com.prime.uc.model.Doctor;
import com.prime.uc.repo.DoctorRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorService {
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@GraphQLMutation(name = "saveDoctor")
    public Doctor saveDoctor(@GraphQLArgument(name = "doctor") Doctor doctor) {
        return doctorRepo.save(doctor);
    }
	
	@GraphQLQuery(name = "getDoctorDetails")
    public List<Doctor> getUserById(@GraphQLArgument(name = "id")UUID id){
    	return doctorRepo.getUserByid(id);
    }
}
