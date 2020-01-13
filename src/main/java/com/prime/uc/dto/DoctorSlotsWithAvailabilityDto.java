
package com.prime.uc.dto;

import java.util.List;

import lombok.Data;
@Data
public class DoctorSlotsWithAvailabilityDto {
	String doctorName;
	String doctorId;
	List<DoctorSlotDto> availableSlots;
	List<DoctorSlotDto> unavailableSlots;
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public List<DoctorSlotDto> getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(List<DoctorSlotDto> availableSlots) {
		this.availableSlots = availableSlots;
	}
	public List<DoctorSlotDto> getUnavailableSlots() {
		return unavailableSlots;
	}
	public void setUnavailableSlots(List<DoctorSlotDto> unavailableSlots) {
		this.unavailableSlots = unavailableSlots;
	}
	
	
}
