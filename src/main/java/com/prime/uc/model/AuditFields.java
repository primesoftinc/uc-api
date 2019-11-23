package com.prime.uc.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import com.prime.uc.config.AuditFieldListener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditFieldListener.class)
public class AuditFields implements Serializable {
      private static final long serialVersionUID = 1745861473941254792L;
 
      

//  	@Column(name="CREATED_BY", nullable=false, length=100, updatable = false)
//  	private String createdBy;

//  	@Column(name="CREATED_BY_ID", nullable=false, length=50, updatable = false)
//  	private String createdById;
  	
//  	@Temporal(TemporalType.TIMESTAMP)
  	@Column(name="created_ts", nullable=false, updatable = false)
  	private Timestamp createdTs;
  	
  	
  	
//	@Column(name="UPDATED_BY", length=100)
//	private String updatedBy;

//	@Column(name="UPDATED_BY_ID", length=50)
//	private String updatedById;
	
//	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_ts")
	private Timestamp updatedTs;
 

}