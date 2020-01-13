package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.UserFavourites;
import com.prime.uc.repo.UserFavouritesRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserFavouritesService {
	
	@Autowired
	private UserFavouritesRepo userFavouritesRepo;
	
	@GraphQLMutation(name ="saveUserFavourites")
	public UserFavourites saveUserFavourites (@GraphQLArgument(name ="userFavourites") UserFavourites userFavourites) {
		 return userFavouritesRepo.save(userFavourites);
	}
	
	@GraphQLMutation(name= "deleteUserFavourites")
	public int deleteUserFavourites(@GraphQLArgument(name ="id")UUID id) {
		return userFavouritesRepo.deleteByBranchId(id);
	}
	
	@GraphQLMutation(name= "getUserFavourites")
	public List<UserFavourites> getUserFavourites(@GraphQLArgument(name ="userId")UUID userId) {
		return userFavouritesRepo.getUserFavourites(userId);
	}
}
