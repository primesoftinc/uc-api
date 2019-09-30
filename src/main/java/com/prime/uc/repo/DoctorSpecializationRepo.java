package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.DoctorSpecialization;


@Repository
public interface DoctorSpecializationRepo extends JpaRepository<DoctorSpecialization, UUID>{

	@Query("select ds from DoctorSpecialization ds")
	List<DoctorSpecialization> getSpecializations();
	
	@Transactional
	@Modifying
	@Query("update DoctorSpecialization ds set ds.isDeleted = 1  where ds.id= ?1")
	void updateDoctorSpecializationIsDeleted(UUID dsId);

}
