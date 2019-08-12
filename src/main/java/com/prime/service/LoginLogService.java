package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.LoginLog;
import com.prime.uc.repo.LoginLogRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class LoginLogService {
	
	@Autowired
	private LoginLogRepo loginLogRepo;
	
	 @GraphQLMutation(name = "saveLoginLog")
	    public LoginLog saveLoginLog(@GraphQLArgument(name = "loginLog") LoginLog loginLog) {
	        return loginLogRepo.save(loginLog);
	    }

}
