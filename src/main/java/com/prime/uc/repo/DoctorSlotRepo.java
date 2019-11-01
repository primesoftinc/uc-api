package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.Doctor;
import com.prime.uc.model.DoctorSlot;

@Repository
public interface DoctorSlotRepo  extends JpaRepository<DoctorSlot, UUID> {

	static List<DoctorSlot> getDoctorSlotById(UUID id) {
		return null;
	}
 
	@Query("select distinct d from Doctor d join fetch d.branch b join fetch d.doctorSlot ds where b.id  =?1  and ds.day =?2")
	   List<Doctor> getDoctorSlotsById(UUID branchId,String day);

	@Query("select DISTINCT  d from Doctor d join fetch d.doctorSlot ds join fetch d.branch b where ds.day IN :days and b.id = :branchId ")
	List<Doctor> getSlotsByDay(@Param("days") String day,@Param("branchId") UUID branchId);

	@Transactional
	@Modifying
	@Query("delete DoctorSlot d where d.slotTime = ?1")
	void deleteAllBySlotTime(String slotTime);

	@Query("select ds from DoctorSlot ds left join ds.doctor d left join d.branch db left join fetch ds.branch b where (db.id = :branchId or b.id = :branchId)  and  ds.day IN :days order by d.id")
	List<DoctorSlot> getSlots(@Param("days") List<String> day,@Param("branchId") UUID branchId);


}
