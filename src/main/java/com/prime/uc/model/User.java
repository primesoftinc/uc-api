package com.prime.uc.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Getter @Setter
@NoArgsConstructor
@ToString
public class User extends BaseEntity{
   
    @Column(nullable = false,name="username")
    private String name;

    @Email
    @Column(nullable = false)
    private String email;
    
    @Column
    private String phone;

    @Column
    private String address;
    
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified = false;

    @JsonIgnore
    private String password;

//    @NotNull
//    @Enumerated(EnumType.STRING)
    private String provider;

    private String providerId;
    
    @OneToMany(mappedBy="user")
    private List<UserRole> userRoles;


}
