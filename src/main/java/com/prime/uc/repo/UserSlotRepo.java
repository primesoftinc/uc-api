package com.prime.uc.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prime.uc.model.UserSlot;

@Repository
public interface UserSlotRepo  extends JpaRepository<UserSlot, UUID> {

}
