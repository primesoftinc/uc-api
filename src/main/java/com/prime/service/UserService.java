package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.BranchUser;
import com.prime.uc.model.User;
import com.prime.uc.repo.DoctorRepo;
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
    
    
    @GraphQLQuery(name = "usersByBranchID")
    public List<BranchUser> getUsersBybrancID(@GraphQLArgument(name = "id") UUID id){
    	return userRepo.getUsersBybranchID(id);
    }
    
    
  /*  @GraphQLMutation(name = "saveUser")
    public User saveUser(@GraphQLArgument(name = "user") User user) {
        return userRepo.save(user);
    }*/
    
    public User saveUser(User user) {
    	return userRepo.save(user);
    }

    @GraphQLQuery(name = "getUser")
    public User getUser(@GraphQLArgument(name = "name") String name,@GraphQLArgument(name="password") String password) {
        return userRepo.getUserDetails(name, password);
	}
    
    @GraphQLQuery(name = "getUserAndBranch")
    public BranchUser getUserAndBranch(@GraphQLArgument(name = "name") String name,@GraphQLArgument(name="password") String password) {
        return userRepo.getUserAndBranchDetails(name, password);
	}
    
    @GraphQLMutation(name = "updateUser")
    public int updateUser(@GraphQLArgument(name = "name") String name ,
    		@GraphQLArgument(name = "firstName") String firstName,
    		@GraphQLArgument(name = "lastName") String lastName,
    		@GraphQLArgument(name = "password") String password,
    		@GraphQLArgument(name = "email") String email,
    		@GraphQLArgument(name = "address") String address,
    		@GraphQLArgument(name = "phone") String phone,
    		@GraphQLArgument(name = "id") UUID id) {
        return userRepo.update(name,firstName,lastName,password,email,address,phone,id);
    }
    

}

