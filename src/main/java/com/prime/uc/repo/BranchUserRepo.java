package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prime.uc.model.BranchUser;

public interface BranchUserRepo extends JpaRepository<BranchUser, UUID>{

}
