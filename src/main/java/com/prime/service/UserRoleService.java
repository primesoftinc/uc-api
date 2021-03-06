package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.UserRole;
import com.prime.uc.repo.UserRoleRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserRoleService {
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	 @GraphQLQuery(name = "userRoles")
	    public List<UserRole> getUserRolesByUser(@GraphQLArgument(name = "id")UUID id){
	    	return userRoleRepo.getUserRolesByUser(id);
	    }
	 @GraphQLMutation(name = "saveUserRole")
	    public UserRole saveUserRole(@GraphQLArgument(name = "UserRole") UserRole userRole) {
//		 userRole.setIsDeleted(false);
	        return userRoleRepo.save(userRole);
		}

}
