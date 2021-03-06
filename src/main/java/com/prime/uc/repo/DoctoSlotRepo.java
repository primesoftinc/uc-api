package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.DoctorSlot;

@Repository
public interface DoctoSlotRepo  extends JpaRepository<DoctorSlot, UUID> {

	static List<DoctorSlot> getDoctorSlotById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Query("select ds from DoctorSlot ds join fetch ds.doctor d where ds.day=?1")
	List<DoctorSlot> getDoctorsByDay(String day);

}
