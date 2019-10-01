package com.prime.service;

import java.util.List;
import java.util.UUID;

import javax.persistence.OrderBy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.DoctorUnavailability;
import com.prime.uc.repo.DoctorUnavailabilityRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorUnavailabilityService {
	
	@Autowired
	private DoctorUnavailabilityRepo doctorUnavailabilityRepo;
	
	@GraphQLMutation(name = "saveDoctorUnavailability")
    public List<DoctorUnavailability> saveDoctorUnavailability(@GraphQLArgument(name = "doctorUnavailability") List<DoctorUnavailability> doctorUnavailability) 
	{
        return doctorUnavailabilityRepo.saveAll(doctorUnavailability);
    }
	

	@GraphQLQuery(name = "getDoctorUnavailabilityByBranch")
    public List<DoctorUnavailability> getDoctorUnavailabilityByBranch(@GraphQLArgument(name = "branchId") UUID branchId ,@GraphQLArgument(name = "date") String date) {
        return doctorUnavailabilityRepo.retriveDoctorUnavailabilityByBranchId(branchId,date);
    }
}
