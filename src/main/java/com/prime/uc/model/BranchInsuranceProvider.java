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
@Table(name = "user_insurance")
@Getter @Setter
@NoArgsConstructor
@ToString

public class BranchInsuranceProvider extends BaseEntity{

	@Column(name="plan_name")
    private String planName;
	
	@Column(name="picture")
    private String picture;
	
	@Column(name="is_active")
    private Boolean isActive = Boolean.TRUE;
	
	@JoinColumn(name="branch_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Branch branch;
	
	@JoinColumn(name="insurance_provider_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private InsuranceProvider insuranceProvider;
	
	
}
