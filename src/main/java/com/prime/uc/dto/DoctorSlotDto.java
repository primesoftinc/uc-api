
package com.prime.uc.dto;

import java.sql.Time;

public interface DoctorSlotDto {
	String getDoctorName();
	String getDoctorId();
	String getSlotId();
	String getday();
	Time getSlotTime();
	String getDoctorUnavailablityId();
	String getSpecializationName();
	Boolean getIsAvailable();	
}
