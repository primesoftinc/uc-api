package com.prime.uc.model;

import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.engine.internal.Cascade;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor_slot")
@Getter @Setter
@NoArgsConstructor
@ToString
public class DoctorSlot extends BaseEntity{
	
	@Column(name="day")
    private String day;
	
	@Column(name="slot_time")
    private Time slotTime;
	
	@JoinColumn(name = "doctor_id")
	@ManyToOne(fetch=FetchType.LAZY )
    private Doctor doctor; 
	
	@JoinColumn(name = "branch_id")
	@ManyToOne(fetch=FetchType.LAZY )
    private Branch branch; 
	
}
