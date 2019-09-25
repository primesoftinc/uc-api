package com.prime.uc.model;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "specialization")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Specialization extends BaseEntity{
	@Column(name="specialization_name")
    private String specializtionName;

	@JoinColumn(name = "branch_id")
	@ManyToOne(fetch=FetchType.LAZY)
    private Branch branch;
	
	@OneToMany(mappedBy="doctor")
	private List<DoctorSpecialization> doctorSpecialization;

}
