package com.prime.uc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

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
@Where(clause="is_deleted=0")
public class DoctorSpecialization extends BaseEntity {
	
	@JoinColumn(name = "doctor_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Doctor doctor;
	
	
	@JoinColumn(name = "specialization_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Specialization specialization;
	
	 @Column(name="is_deleted")
	 private Boolean isDeleted  = Boolean.FALSE;	

}
