package com.prime.uc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Role extends BaseEntity{
	
	@Column(name="role_name")
    private String roleName;
	
	@JoinColumn(name = "branch_id")
	@ManyToOne(fetch=FetchType.LAZY)
    private Branch branch;
	
	@OneToMany(mappedBy="role")
	private List<RolePrivilege> rolePrivileges;

}
