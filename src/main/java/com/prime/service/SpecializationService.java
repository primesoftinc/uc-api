package com.prime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.Branch;
import com.prime.uc.model.Specialization;
import com.prime.uc.repo.SpecializationRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SpecializationService {
	
	@Autowired
	private SpecializationRepo specializationRepo;
	
	@GraphQLMutation(name = "saveSpecialization")
    public Specialization saveSpecialization(@GraphQLArgument(name = "specialization") Specialization specialization) {
        return specializationRepo.save(specialization);
    }
	
	@GraphQLQuery(name = "getSpecilaization")
    public List<Specialization>getSpecilaization() {
        return specializationRepo.retrive();
    }
	
}
