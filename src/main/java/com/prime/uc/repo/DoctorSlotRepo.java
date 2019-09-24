package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.DoctorSlot;

@Repository
public interface DoctorSlotRepo  extends JpaRepository<DoctorSlot, UUID> {

	static List<DoctorSlot> getDoctorSlotById(UUID id) {
		return null;
	}
 
	@Query("select ds from DoctorSlot ds join fetch ds.doctor d where d.id = ?1 and ds.day =?2")
	   List<DoctorSlot> getDoctorSlotsById(UUID id, String day);

}
