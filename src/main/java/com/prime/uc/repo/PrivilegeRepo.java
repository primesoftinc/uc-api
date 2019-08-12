package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prime.uc.model.Privilege;

public interface PrivilegeRepo extends JpaRepository<Privilege, UUID>{

}
