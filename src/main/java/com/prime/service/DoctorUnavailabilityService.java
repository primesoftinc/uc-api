package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.DoctorUnavailability;
import com.prime.uc.repo.DoctorUnavailabilityRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorUnavailabilityService {
	
	@Autowired
	private DoctorUnavailabilityRepo doctorUnavailabilityRepo;
	
	@GraphQLMutation(name = "saveDoctorUnavailability")
    public DoctorUnavailability saveDoctorUnavailability(@GraphQLArgument(name = "doctorUnavailability") DoctorUnavailability doctorUnavailability) 
	{
        return doctorUnavailabilityRepo.save(doctorUnavailability);
    }
}
