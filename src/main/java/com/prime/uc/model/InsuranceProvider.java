package com.prime.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="insurance_provider")
@Getter @Setter
@NoArgsConstructor
@ToString
public class InsuranceProvider extends BaseEntity {
	
	@Column(name="provider_name")
    private String providerName;
	
	@Column(name="phone_number")
    private String phoneNumber;
}
