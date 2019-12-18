package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.repo.InsuranceProviderRepo;

import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class InsuranceProviderService {
	@Autowired
	private InsuranceProviderRepo insuranceProviderRepo ;

}
