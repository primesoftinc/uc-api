package com.prime.uc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "doctor")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Doctor extends BaseEntity {

	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@Column(name = "qualification")
	private String qualification;

	@Column(name = "doctor_name")
	private String doctorName;
	
	@OneToMany(mappedBy="doctor")
	private List<DoctorSlot> doctorSlot;
	
	@JoinColumn(name="branch_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Branch branch;
	

}
