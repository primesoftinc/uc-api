package com.prime.uc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.aspectj.weaver.World;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_slot_symptom")
@Getter @Setter
@NoArgsConstructor
@ToString
public class UserSlotSymptom extends BaseEntity{
	
	@JoinColumn(name="user_slot_id")
	@ManyToOne(fetch=FetchType.LAZY)
	private UserSlot userSlot;
	
	@Column(name="symptom")
	private String Symptom;
	
	@Column(name="duration")
	private String duration;
	
	@Column(name="pain_level")
	private String painLevel;
	
	@Column(name="verified_symptom")
	private String verifiedSymptom;
	
}
