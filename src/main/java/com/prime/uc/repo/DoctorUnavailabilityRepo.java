package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.DoctorUnavailability;

@Repository
public interface DoctorUnavailabilityRepo  extends JpaRepository<DoctorUnavailability, UUID> {

	
	@Query("select du from DoctorUnavailability du join fetch du.doctorSlot ds  where  du.branch.id =?1 and du.date = ?2")
	List<DoctorUnavailability> retriveDoctorUnavailabilityByBranchId(UUID branchId, String date);

	@Transactional
	@Modifying
	@Query("delete DoctorUnavailability du WHERE du.id IN :ids")
	int deleteAllById(@Param("ids")List<UUID> ids);

}
