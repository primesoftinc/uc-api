package com.prime.uc.repo;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prime.uc.dto.DoctorSlotDto;
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

	@Query(value="(select d.doctor_name as doctorName, BIN_TO_UUID(d.id) as doctorId, ds.slot_time as slotTime, BIN_TO_UUID(ds.id) as slotId from branch b\n" + 
			"left join doctor d on d.branch_id = b.id\n" + 
			"\n" + 
			"left join doctor_slot ds on ds.doctor_id = d.id \n" + 
			"\n" + 
			"where b.id = :branchId)\n" + 
			"union \n" + 
			"\n" + 
			"(select \"All Slots\" as doctorName, null as doctorId, ds.slot_time as slotTime, BIN_TO_UUID(ds.id) as slotId  from branch b\n" + 
			"left join doctor_slot ds on ds.branch_id = b.id and doctor_id is null\n" + 
			"\n" + 
			"where b.id = :branchId) ", nativeQuery= true)
	List<DoctorSlotDto> getSlotsByBranchId(@Param("branchId") UUID branchId);
	
	
	@Query("select ds from DoctorSlot ds left join ds.doctor d  join fetch ds.branch b where  ds.day IN :days and b.id = :branchId and d.id IS NULL")
	List<DoctorSlot> getAllSlots(@Param("days") List<String> day,@Param("branchId") UUID branchId);
	
	
	@Query(value="SELECT d.doctor_name          AS doctorName, \n" + 
			"       Cast(d.id AS VARCHAR)  AS doctorId, \n" + 
			"       Cast(ds.id AS VARCHAR)  AS slotId, \n" + 
			"       ds.day                 AS day, \n" + 
			"       ds.slot_time           AS slotTime, \n" + 
			"       Cast(du.id AS VARCHAR) AS doctorUnavailablityId, \n" + 
			"       s.specialization_name  AS specializationName, \n" + 
			"       CASE \n" + 
			"         WHEN du.id IS NULL THEN true \n" + 
			"         ELSE false \n" + 
			"       END                    AS isAvailable \n" + 
			"FROM   doctor_slot ds \n" + 
			"       INNER JOIN doctor d \n" + 
			"               ON d.id = ds.doctor_id \n" + 
			"       LEFT JOIN doctor_unavailability du \n" + 
			"              ON ds.id = du.doctor_slot_id \n" + 
			"                 AND du.date = :date \n" + 
			"       INNER JOIN doctor_specialization dsp \n" + 
			"               ON dsp.doctor_id = d.id \n" + 
			"       INNER JOIN specialization s \n" + 
			"               ON s.id = dsp.specialization_id \n" + 
			"WHERE  ds.day = :day \n" + 
			"       AND ds.branch_id = :branchId \n" + 
			"UNION \n" + 
			"(SELECT 'All slots'            AS doctorName, \n" + 
			"        NULL                   AS doctorId, \n" + 
			"        Cast(ds.id AS VARCHAR) AS slotId, \n" + 
			"        ds.day                 AS day, \n" + 
			"        ds.slot_time           AS slotTime, \n" + 
			"        Cast(du.id AS VARCHAR) AS doctorUnavailablityId, \n" + 
			"        'specialization'       AS specializationName, \n" + 
			"        CASE \n" + 
			"          WHEN du.id IS NULL THEN true \n" + 
			"          ELSE false \n" + 
			"        END                    AS isAvailable \n" + 
			" FROM   branch b \n" + 
			"        LEFT JOIN doctor_slot ds \n" + 
			"               ON ds.branch_id = b.id \n" + 
			"                  AND doctor_id IS NULL \n" + 
			"        LEFT JOIN doctor_unavailability du \n" + 
			"               ON ds.id = du.doctor_slot_id \n" + 
			"                  AND du.date = :date \n" + 
			" WHERE  b.id = :branchId \n" + 
			"        AND ds.day = :day)", nativeQuery=true)
	List<DoctorSlotDto> getSlotsFor(@Param("branchId") UUID branchId, @Param("day") String day,@Param("date") String date);

	

}
