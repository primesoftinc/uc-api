package com.prime.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "doctor_unavailability")
@NoArgsConstructor
@ToString
public class DoctorUnavailability extends BaseEntity {
	@Column(name = "date")
	private String date;

	@Column(name = "slot")
	private String slot;
	
	@Column(name = "day")
	private String day;
	
	@JoinColumn(name = "doctor_slot_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private DoctorSlot doctorSlot;
	
	@JoinColumn(name = "branch_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Branch branch;

}
