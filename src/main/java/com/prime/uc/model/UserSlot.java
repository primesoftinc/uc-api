package com.prime.uc.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
@Table(name = "user_slot")
@Getter @Setter
@NoArgsConstructor
@ToString
public class UserSlot extends BaseEntity{
	@JoinColumn(name="doctor_slot_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private DoctorSlot doctorSlot;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	@Column(name="date")
	private DateFormat date;
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@Column(name="conformation_code")
	private String conformationCode;
	
	@Column(name="cancelled")
	private Boolean cancelled;
	
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
	
	
	
	
	
	

}
