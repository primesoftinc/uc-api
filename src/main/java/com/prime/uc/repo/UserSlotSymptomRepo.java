package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserSlot;
import com.prime.uc.model.UserSlotSymptom;

@Repository
public interface UserSlotSymptomRepo extends JpaRepository<UserSlotSymptom, UUID>{

	List<UserSlot> getUserSloySymptomById(UUID id);

}
