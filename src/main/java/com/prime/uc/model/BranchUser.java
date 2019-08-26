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
@Table(name = "branch_user")
@Getter @Setter
@NoArgsConstructor
@ToString
public class BranchUser extends BaseEntity{

    @JoinColumn(name="branch_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Branch branch;
    
    @JoinColumn(name="user_id")
    @ManyToOne(fetch=FetchType.LAZY)
    private User user;
    
    @Column(name="type")
    private String type;
    
}
