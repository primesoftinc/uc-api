package com.prime.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
	
////	@GraphQLMutation(name = "saveBranchUser")
//    public BranchUser saveBranchUser(@GraphQLArgument(name = "branchUser") BranchUser branchUser) {
//        return branchUserRepo.save(branchUser);
//    }
	
	@GraphQLMutation(name = "saveBranchUser")
    public BranchUser saveBranchUserWithUser(@GraphQLArgument(name = "branchUser") BranchUser branchUser) {
		User user= userRepo.save(branchUser.getUser());
		branchUser.setUser(user);
		List<UserRole> userRoles = user.getUserRoles().stream().map(ur -> {
			ur.setUser(user);
			return ur;
		}).collect(Collectors.toList());
		userRoleRepo.saveAll(userRoles);
//		UUID uid =user.getId();
		if(user.getIsDoctor()) {
			Doctor doctor = new Doctor();
			doctor.setUser(user);
			doctor.setBranch(branchUser.getBranch());
			doctor.setDoctorName(user.getName());
			doctorRepo.save(doctor);
			List<DoctorSpecialization> doctorSpecialization = user.getDoctor().get(0).getDoctorSpecializations().stream().map(ds -> {
				ds.setDoctor(doctor);
				return ds;
			}).collect(Collectors.toList());
			 
			doctorSpecializationRepo.saveAll(doctorSpecialization);
		}
//		UUID urid = ur.getId();
        return branchUserRepo.save(branchUser);
    }
}
