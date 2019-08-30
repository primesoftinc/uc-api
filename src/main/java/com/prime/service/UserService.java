package com.prime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.User;
import com.prime.uc.repo.UserRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	
    @GraphQLQuery(name = "greeting")
    public String greeting() {
        return "hello world";
    }
    
    
    @GraphQLQuery(name = "users")
    public List<User> getUsers(){
    	return userRepo.retrive();
    }
    
    
    @GraphQLMutation(name = "saveUser")
    public User saveUser(@GraphQLArgument(name = "user") User user) {
        return userRepo.save(user);
    }
    
    @GraphQLQuery(name = "getUser")
    public User getUser(@GraphQLArgument(name = "name") String name,@GraphQLArgument(name="password") String password) {
        return userRepo.getUserDetails(name, password);
	}
    

}

