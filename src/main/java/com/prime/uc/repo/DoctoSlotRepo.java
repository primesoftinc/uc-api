package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.DoctorSlot;

@Repository
public interface DoctoSlotRepo  extends JpaRepository<DoctorSlot, UUID> {

}
