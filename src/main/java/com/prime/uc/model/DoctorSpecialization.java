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
@Table(name = "doctor_specialization")
@Getter   
@Setter
@NoArgsConstructor
@ToString
public class DoctorSpecialization extends BaseEntity {
	
	@JoinColumn(name = "doctor_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Doctor doctor;
	
	@Column(name="specialization")
	private String specialization;
	

}