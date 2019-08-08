package com.prime.uc.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prime.uc.exception.ResourceNotFoundException;
import com.prime.uc.model.User;
import com.prime.uc.repo.UserRepo;
import com.prime.uc.security.CurrentUser;
import com.prime.uc.security.UserPrincipal;

@RestController
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepo.findById(UUID.fromString(userPrincipal.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
