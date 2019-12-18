package com.prime.uc.model;

import java.util.List;

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
public class UserInsurance extends BaseEntity{
	
	@Column(name="policy_name")
    private String policyName;
	
	@Column(name="policy_number")
    private String policyNumber;
	
	@Column(name="provider")
    private String provider;
	
	@Column(name="plan_name")
    private String planName;
	
	@Column(name="picture")
    private String picture;
	
	@Column(name="is_active")
    private Boolean isActive = Boolean.FALSE;
	
	@JoinColumn(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
	
}
