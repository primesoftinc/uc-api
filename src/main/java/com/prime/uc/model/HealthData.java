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
@Table(name = "health_data")
@Getter @Setter
@NoArgsConstructor
@ToString
public class HealthData  extends BaseEntity{

	@Column(name="allergic_medicines")
    private String allergicMedicines;
	
	@Column(name="health_issues")
    private String healthIssues;
	
	@Column(name="height")
    private String height;
	
	@Column(name="weight")
    private String weight;
	
	@Column(name="gender")
    private String gender;
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
}
