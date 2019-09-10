package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserSlot;

@Repository
public interface UserSlotRepo  extends JpaRepository<UserSlot, UUID> {

	
	@Query("select us from UserSlot us join fetch us.branch where us.branch.id = ?1")
	UserSlot retriveUserSlotByUserBranchId(UUID id);
	
	@Query("select us from UserSlot us join fetch us.doctorSlot ds join fetch ds.doctor d where d.id =?1")
	List<UserSlot> getAppointmentsOrderByDate(UUID doctorId);

	
	@Query("select us from UserSlot us join fetch us.doctorSlot join fetch us.user where  us.branch.id =?1 and us.date = ?2")
	List<UserSlot> retriveAppointmentsByBranchId(UUID branchId,String date);

	@Query("select us from UserSlot us join fetch us.doctorSlot join fetch us.user where us.date between ?1 and ?2")
	List<UserSlot> getAppointmentsBetweenDate(String fromDate, String toDate);

}
