package com.prime.uc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "branch")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Branch extends BaseEntity{
	
	@Column(name="branch_name")
    private String branchName;
	
	@Column(name="code")
    private String code;
	
	@Column(name="lat")
    private Double lat;
	
	@Column(name="lon")
    private Double lon;
	
	@Email
	@Column(name="email")
    private String email;
	
	@Column(name="land_phone")
    private String landPhone;
	
	@Column(name="mobile")
    private String mobile;
	
	@Column(name="contact")
    private String contact;
	
	@Column(name="address")
	private String address;
	
	
}
