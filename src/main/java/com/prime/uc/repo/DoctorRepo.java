package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.Doctor;
@Repository
public interface DoctorRepo extends JpaRepository<Doctor, UUID>{

	List<Doctor> getDoctorById(UUID id);

	@Query("select d from Doctor d join fetch d.branch b join fetch d.user u where b.id = ?1")
	List<Doctor> getDoctorsByBranch(UUID id);
}
