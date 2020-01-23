package com.prime.uc.model;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    
    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Boolean emailVerified;
    
    @Column
    private String password;

//    @NotNull
//    @Enumerated(EnumType.STRING)
    private String provider;

    private String providerId;
    
    @Column(name="first_name")
    private String firstName;
    
    @Column(name="last_name")
    private String lastName;
    
    @Column(name="is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
    
    @Column(name="status")
    private String status;
    
    @Column(name ="lattitude")
    private Double  lattitude;
    
    @Column(name="longitude")
    private Double longitude;
    
    @OneToMany(mappedBy="user",fetch=FetchType.LAZY)
    private List<UserRole> userRoles;
    
    @Transient
    private List<String> selectedRoles;
    
    @Transient
    private List<String> selectedSpecializations;
    
    public String getFullName() {
        String fullName =
                Stream.of(this.firstName, this.lastName)
                      .filter(s -> s != null && !s.isEmpty())
                      .collect(Collectors.joining(" "));
        return fullName;
    }
    
    @OneToMany(mappedBy="user",fetch=FetchType.LAZY)
    private List<Doctor> doctors;
    
    @Transient
    private Boolean isDoctor;
    
    @OneToMany(mappedBy="user")
    private List<BranchUser> branchUser;
    
    
    


}
