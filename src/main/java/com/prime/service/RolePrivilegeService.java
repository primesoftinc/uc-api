package com.prime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.uc.model.RolePrivilege;
import com.prime.uc.repo.RolePrivilegeRepo;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class RolePrivilegeService {
	
	@Autowired
	private RolePrivilegeRepo rolePrivilegeRepo;
	
	@GraphQLMutation(name = "saveRolePrivilege")
    public RolePrivilege saveRolePrivilege(@GraphQLArgument(name = "rolePrivilege") RolePrivilege rolePrivilege) {
        return rolePrivilegeRepo.save(rolePrivilege);
    }
}
