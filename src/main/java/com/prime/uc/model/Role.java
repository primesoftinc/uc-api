package com.prime.uc.model;

import javax.persistence.Entity;
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
public class Role {

    private String roleName;
    
    private Branch branch;
}
