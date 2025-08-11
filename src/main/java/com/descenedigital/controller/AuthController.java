package com.descenedigital.controller;



import com.descenedigital.model.Role;
import com.descenedigital.model.User;
import com.descenedigital.repo.UserRepository;
import com.descenedigital.dto.auth.*;
import com.descenedigital.service.AuthService;
import org.springframework.security.core.Authentication;
import com.descenedigital.security.CustomUserDetailsService;
import com.descenedigital.security.JwtUtil;
import lombok.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}





