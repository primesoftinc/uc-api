package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prime.uc.model.RolePrivilege;

public interface RolePrivilegeRepo extends JpaRepository<RolePrivilege, UUID>{

}
