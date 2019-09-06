package com.prime.uc.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class UserRole  extends BaseEntity{
	
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Role role;

}
