package com.prime.uc.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class RolePrivilege extends BaseEntity{

	@JoinColumn(name = "role_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;
	
	@JoinColumn(name = "privilege_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Privilege privilege;
}
