package com.prime.uc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_slot")
@Getter @Setter
@NoArgsConstructor
@ToString
public class UserSlot extends BaseEntity{
	@JoinColumn(name="doctor_slot_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private DoctorSlot doctorSlot;
	
	@Column(name="date")
	private String date;
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@Column(name="confirmation_code")
	private String confirmationCode;
	
	@Column(name="cancelled")
	private Boolean cancelled;
	
	@Column(name="`status`")
	private String status;
	
	@Column(name="attended")
	private String attended;
	
	@Column(name="notes")
	private String notes;
	
	@JoinColumn(name="branch_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Branch branch;
	
	@Column(name="lat")
	private Double lat;
	
	@Column(name="lon")
	private Double lon;
	
	@OneToMany(mappedBy="userSlot")
	private List<UserSlotSymptom> userSlotSymptom;	
	
}
