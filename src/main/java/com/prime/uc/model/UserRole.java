package com.prime.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@Where(clause="is_deleted=false")
public class UserRole  extends BaseEntity{
	
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@JoinColumn(name = "role_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Role role;
	
    @Column(name="is_deleted")
    private Boolean isDeleted  = Boolean.FALSE;
	

}
