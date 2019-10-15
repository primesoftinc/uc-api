package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.HealthData;

@Repository
public interface HealthDataRepo extends JpaRepository<HealthData, UUID>{

}
