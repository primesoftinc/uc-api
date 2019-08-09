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
@Table(name = "loginlog")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginLog extends BaseEntity{
	
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private User User;

	@Column(name="user_name")
	private String userName;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name="log_time")
	private String logTime;
	
}
