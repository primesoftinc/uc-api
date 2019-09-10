package com.prime.uc.repo;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.prime.uc.model.Specialization;

@Repository
public interface SpecializationRepo extends JpaRepository<Specialization, UUID>{

	
	@Query("select specialization from Specialization specialization")
	List<Specialization> retrive();

}
