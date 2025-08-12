package com.descenedigital.service;


import com.descenedigital.dto.Response;
import com.descenedigital.dto.auth.LoginRequest;
import com.descenedigital.dto.register.RegisterRequest;
import com.descenedigital.dto.register.RegisterResponse;
import com.descenedigital.mapper.UserMapper;
import com.descenedigital.model.Role;
import com.descenedigital.model.User;
import com.descenedigital.repo.UserRepository;
import com.descenedigital.security.CustomUserDetailsService;
import com.descenedigital.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.descenedigital.dto.auth.LoginResponse;

import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;
    private final UserMapper userMapper;

    public Response<?> register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return Response.builder()
                    .success(false)
                    .message("Username already taken")
                    .build();
        }
        System.out.println(request);
        try {
            User user = userMapper.toEntity(request);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        User user = userMapper.toEntity(request);
        System.out.println(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER.name());

        userRepo.save(user);

        RegisterResponse responseDto = userMapper.toDto(user);

        return Response.builder()
                .success(true)
                .message("User registered successfully")
                .build();
    }

    public Response<?> login(LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    ));

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            User user = userRepo.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return Response.builder()
                    .success(true)
                    .message("User Login Successfully")
                    .data( LoginResponse.builder()
                            .role(user.getRole())
                            .token(token)
                            .build())
                    .build();
        } catch (BadCredentialsException e) {
            return Response.builder()
                    .success(false)
                    .message("Invalid username or password")
                    .build();
        }
    }

}
