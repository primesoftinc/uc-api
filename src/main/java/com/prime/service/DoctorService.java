package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Doctor;
import com.prime.uc.repo.DoctorRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorService {
	
	@Autowired
	private DoctorRepo DoctorRepo;
	
	@GraphQLMutation(name = "saveDoctor")
    public Doctor saveDoctor(@GraphQLArgument(name = "doctor") Doctor doctor) {
        return DoctorRepo.save(doctor);
    }
}
