package com.prime.uc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="privilege")
@NoArgsConstructor
public class Privilege extends BaseEntity{
	
	@Column(name="privilege_code")
	private String privilegeCode;
	
	@Column(name="category")
	private String category;
	
	@Column(name="section")
	private String section;
	
	@Column(name="type")
	private String type;
	
	@OneToMany(mappedBy="role")
	private List<RolePrivilege> rolePrivileges;
	

}
