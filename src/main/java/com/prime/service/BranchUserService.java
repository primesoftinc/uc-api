package com.prime.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
		boolean isDeleted = false;
		isDoctor = branchUser.getUser().getIsDoctor();
//		List<UserRole> roleIds = branchUser.getUser().getUserRoles();
//		List<UserRole> saveRoles = userRepo.getRolesByUserId(branchUser.getUser().getId());
//		ArrayList<UUID> id = new ArrayList<UUID>();
//		ArrayList<UUID> roleid = new ArrayList<UUID>();
//		for(UserRole sr : saveRoles) {
//			id.add(sr.getRole().getId());	
//			
//		}
//		for(UserRole sr : roleIds) {
//			sr.setIsDeleted(isDeleted);
//			
//		}
//		for(int i =0;i<id.size();i++) {
//			
//				if(!roleid.contains(id.get(i))) {
//					userRoleRepo.updateRoleIsDeleted(id.get(i));
//				}
//			
//		}
		branchUser.getUser().setIsDeleted(isDeleted);
		List<DoctorSpecialization> doctorSpecialization = new ArrayList<DoctorSpecialization>();
		//List<DoctorSpecialization> doctorSpecialization =  branchUser.getUser().getDoctors().get(0).getDoctorSpecializations();
		
		User user= userRepo.save(branchUser.getUser());
		user.setIsDoctor(isDoctor);
		user.setUserRoles(branchUser.getUser().getUserRoles());
		
		List<UserRole> userRoles = user.getUserRoles().stream().map(ur -> {
			ur.setUser(user);
			return ur;
		}).collect(Collectors.toList());
		
		userRoleRepo.saveAll(userRoles);
		user.setDoctors(branchUser.getUser().getDoctors());
		branchUser.setUser(user);
		UUID uid =user.getId();
		if(isDoctor) {
//			doctorSpecialization = branchUser.getUser().getDoctors().get(0).getDoctorSpecializations();
			Doctor doctor = null;
			if(branchUser.getUser().getDoctors().stream().findFirst().isPresent()) {
				
				doctor = branchUser.getUser().getDoctors().stream().findFirst().get();
				doctorSpecialization = doctor.getDoctorSpecializations();	
				doctor.setUser(user);
//				if(StringUtils.isEmpty(doctor.getId())) {
					doctor.setBranch(branchUser.getBranch());
					doctor.setDoctorName(user.getName());
//				}
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
       Optional<Doctor> oDoctor = branchUser.getUser().getDoctors().stream().findFirst();
       if(oDoctor.isPresent() ) {
    	   Doctor doctor = oDoctor.get();
    	   if(doctor.getDoctorSpecializations().size() > 0) {
        	   branchUser.getUser().setIsDoctor(true);
    	   }
       }
	return branchUser;
       
    }
	
	@GraphQLMutation(name = "updateBranchUserIsDeleted")
	public int updateBranchUserIsDeleted(@GraphQLArgument(name = "userId") UUID userId) {
		
		return branchUserRepo.updateBranchUserIsDeleted(userId);
		
	}
	
	
	
}
