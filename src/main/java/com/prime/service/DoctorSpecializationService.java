package com.prime.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.DoctorSpecialization;
import com.prime.uc.repo.DoctorSpecializationRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class DoctorSpecializationService {
	
	@Autowired
	private DoctorSpecializationRepo doctorSpecializationRepo;
	
	@GraphQLMutation(name = "saveDoctorSpecialization")
    public DoctorSpecialization saveDoctorSpecialization(@GraphQLArgument(name = "user") DoctorSpecialization doctorSpecialization) {
        return doctorSpecializationRepo.save(doctorSpecialization);
    }
	
	@GraphQLQuery(name = "getDoctorSpecialization")
    public List<DoctorSpecialization> getDoctorSpecialization(){
    	return doctorSpecializationRepo.getSpecializations();
    }
}
