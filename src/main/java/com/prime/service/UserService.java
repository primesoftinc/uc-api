package com.prime.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.BranchUser;
import com.prime.uc.model.Doctor;
import com.prime.uc.model.DoctorSpecialization;
import com.prime.uc.model.User;
import com.prime.uc.model.UserRole;
import com.prime.uc.repo.BranchUserRepo;
import com.prime.uc.repo.DoctorRepo;
import com.prime.uc.repo.DoctorSpecializationRepo;
import com.prime.uc.repo.UserRepo;
import com.prime.uc.repo.UserRoleRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	@Autowired
	private DoctorSpecializationRepo doctorSpecializationRepo;
	

	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private BranchUserRepo branchUserRepo;
	
    @GraphQLQuery(name = "greeting")
    public String greeting() {
        return "hello world";
    }
    
    
    @GraphQLQuery(name = "users")
    public List<User> getUsers(){
    	return userRepo.retrive();
    }
    
    
    @GraphQLQuery(name = "usersByBranchID")
    public List<BranchUser> getUsersBybrancID(@GraphQLArgument(name = "id") UUID id,@GraphQLArgument(name = "isDeleted") Boolean isDeleted){
    	return userRepo.getUsersBybranchID(id,false);
    }
    
    
   /* @GraphQLMutation(name = "saveUser")
    public User saveUser(@GraphQLArgument(name = "user") User user) {
        return userRepo.save(user);
    }*/
    
    public User saveUser(User user) {
    	return userRepo.save(user);
    }
    
    @GraphQLMutation(name = "deleteUser")
    public String deleteBranchById(@GraphQLArgument(name = "id") UUID id) {
    	List<User> user = userRepo.getUserById(id);
   	 	User u = user.stream().findFirst().get();
   	 	Doctor d = u.getDoctors().stream().findFirst().get();
   	 	List<DoctorSpecialization> doctorSpecialization = d.getDoctorSpecializations();
   	 if(doctorSpecialization.stream().findFirst().isPresent()) {
   		 u.setIsDoctor(true);
   	 }
   	 
	   	 if(u.getIsDoctor()) {
	   		for (DoctorSpecialization ds : doctorSpecialization) {
				 UUID dsId = ds.getId();
				 doctorSpecializationRepo.updateDoctorSpecializationIsDeleted(dsId);
			}
			doctorRepo.updateIsDeleted(true, d.getId());
		 
		 }
	   	 List<UserRole> userRole = u.getUserRoles();
	   	 for (UserRole userrole : userRole) {
	   		 UUID urId = userrole.getId();
	   		 userRoleRepo.updateRoleIsDeleted(urId);
		}
	   	BranchUser bu = u.getBranchUser().stream().findFirst().get();
	   	branchUserRepo.updateBranchUserIsDeleted(bu.getId());
    	userRepo.delete(id);
         return "delete sucessful";
    }
	
    
    @GraphQLMutation(name = "getUser")
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
    
//    @GraphQLQuery(name = "getDoctorSpecializationById")
//    public User getDoctorSpecializationById(@GraphQLArgument(name = "id") UUID id) {
//        return userRepo.getDoctorSpecializationsById(id);
//	}
    
    @GraphQLMutation(name = "createUser")
    public User createUser(@GraphQLArgument(name = "user") User user ) {
    	user.setEmailVerified(false);
    	return userRepo.save(user);
    }

    @GraphQLQuery(name = "getUserNames")
    public List<String> getUserNames() {
        return userRepo.getUserNames();
	}
    
    @GraphQLMutation(name = "updateUserAddress")
    public int updateUserAddress(@GraphQLArgument(name = "userId") UUID userId,@GraphQLArgument(name = "address") String address ) {
    	return userRepo.updateUserAddress(userId,address);
    }
    
    @GraphQLQuery(name= "getUserById")
    public  List<User> getUser(@GraphQLArgument(name="id") UUID id) {
    	return userRepo.getUserById(id);
    }
}

