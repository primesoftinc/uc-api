package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.prime.uc.model.Branch;
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

public class BranchUserService {
	
	@Autowired
	private BranchUserRepo branchUserRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserRoleRepo userRoleRepo;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Autowired
	private DoctorSpecializationRepo doctorSpecializationRepo;
	
	@GraphQLMutation(name = "saveBranchUserDuplicate")
    public BranchUser saveBranchUser(@GraphQLArgument(name = "branchUser") BranchUser branchUser) {
       return branchUserRepo.save(branchUser);
    }
	
	
	 @GraphQLMutation(name = "deleteBranchUserById")
	    public String deleteBranchUserById(@GraphQLArgument(name = "id") UUID id) {
	    	branchUserRepo.deleteById(id);
	         return "delete sucessful";
	    }
	
	@GraphQLMutation(name = "saveBranchUser")
    public BranchUser saveBranchUserWithUser(@GraphQLArgument(name = "branchUser") BranchUser branchUser) {
		boolean isDoctor;
		isDoctor = branchUser.getUser().getIsDoctor();
		List<DoctorSpecialization> doctorSpecialization = new ArrayList<DoctorSpecialization>();
		//List<DoctorSpecialization> doctorSpecialization =  branchUser.getUser().getDoctors().get(0).getDoctorSpecializations();
		User user= userRepo.save(branchUser.getUser());
		user.setUserRoles(branchUser.getUser().getUserRoles());
		branchUser.setUser(user);
		List<UserRole> userRoles = user.getUserRoles().stream().map(ur -> {
			ur.setUser(user);
			return ur;
		}).collect(Collectors.toList());
		userRoleRepo.saveAll(userRoles);
		UUID uid =user.getId();
		if(isDoctor) {
			doctorSpecialization = branchUser.getUser().getDoctors().get(0).getDoctorSpecializations();
			Doctor doctor = null;
			if(branchUser.getUser().getDoctors().stream().findFirst().isPresent()) {
				doctor = branchUser.getUser().getDoctors().stream().findFirst().get();
				if(StringUtils.isEmpty(doctor.getId())) {
					doctor.setUser(user);
					doctor.setBranch(branchUser.getBranch());
					doctor.setDoctorName(user.getName());
				}
			}
			doctorRepo.save(doctor);
//			List<DoctorSpecialization> doctorSpecialization = user.getDoctors().get(0).getDoctorSpecializations();
			for (DoctorSpecialization ds : doctorSpecialization) {
				ds.setDoctor(doctor);
			}
			
//			doctorSpecialization.stream().map(ds -> {
//				ds.setDoctor(doctor);
////				ds.setSpecialization(ds.getSpecialization());
//				return ds;
//			}).collect(Collectors.toList());
			 
			doctorSpecializationRepo.saveAll(doctorSpecialization);
		}
//		UUID urid = ur.getId();
        return branchUserRepo.save(branchUser);
    }
	
	@GraphQLQuery(name = "getBranchUserById")
    public BranchUser getBranchUserById(@GraphQLArgument(name = "id") UUID id) {
       BranchUser branchUser = branchUserRepo.getBranchUserById(id);
       if(branchUser.getUser().getDoctors().get(0).getDoctorSpecializations().size()!=0) {
    	   branchUser.getUser().setIsDoctor(true);
       }
	return branchUser;
       
    }
	
	
	
}
