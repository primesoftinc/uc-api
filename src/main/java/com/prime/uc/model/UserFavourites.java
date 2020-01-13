package com.prime.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="user_favourites")
public class UserFavourites extends BaseEntity{
	
	@JoinColumn(name= "user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	@JoinColumn(name= "branch_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private Branch branch;
	
}
