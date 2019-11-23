package com.prime.uc.config;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.prime.uc.model.AuditFields;
import com.prime.uc.security.UserPrincipal;

public class AuditFieldListener {
	private static final Log log = LogFactory.getLog(AuditFieldListener.class);

	@PrePersist
	public void persistAuditFields(AuditFields auditFields) {
		auditFields.setCreatedTs(new Timestamp(Instant.now().toEpochMilli()));
		auditFields.setUpdatedTs(new Timestamp(Instant.now().toEpochMilli()));
	}

	@PreUpdate
	public void updateAuditFields(AuditFields auditFields) {

		auditFields.setUpdatedTs(new Timestamp(Instant.now().toEpochMilli()));
	}

	public static String getUserName() {
		String userName = "unknown";
		try {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			userName = userDetails.getUsername();
		} catch (Exception e) {
			log.error("Exception getting user name from SecurityContextHolder! " + e);
		}
		return userName;
	}

//	public static UserPrincipal getUser() {
//		UserPrincipal userDetails = new UserPrincipal();
//		try {
//			userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		} catch (Exception e) {
//			try {
//				String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//				userDetails.setUsername(userName);
//				return userDetails;
//			} catch (Exception _e) {
//				log.error("Exception getting user from SecurityContextHolder! " + e);
//			}
//		}
//		return userDetails;
//	}
}
