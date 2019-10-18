package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

	@Query("select us from UserSlot us join fetch us.doctorSlot join fetch us.user where us.branch.id = ?1 and us.date between ?2 and ?3 ")
	List<UserSlot> getAppointmentsBetweenDate(UUID branchId ,String fromDate, String toDate);
	
	@Transactional
	@Modifying
	@Query("update UserSlot us set us.notes = ?1 where us.id =?2")
	int  updateUserSlotNotes(String notes, UUID id);
	
	@Transactional
	@Modifying
	@Query(value = "update UserSlot us set us.cancelled = ?2  where us.id =?1")
	int  updateUserSlotCancell( UUID id,Boolean s);


	@Transactional
	@Modifying
	@Query("update UserSlot us set us.status = ?1 where us.id =?2")
	int updateUserSlotStatus(String status, UUID id);

	@Transactional
	@Modifying
	@Query("update UserSlot us set us.attended = ?1 where us.id =?2")
	void updateUserSlotattended(UUID id, String b);

	
	@Query("select  us from UserSlot us join fetch us.user u where u.id = ?1")
	List<UserSlot> retrieveByUserId(UUID userId);

}
