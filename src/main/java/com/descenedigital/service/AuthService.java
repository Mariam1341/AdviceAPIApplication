package com.descenedigital.service;


import com.descenedigital.dto.Response;
import com.descenedigital.dto.auth.JwtResponse;
import com.descenedigital.dto.auth.LoginRequest;
import com.descenedigital.dto.auth.RegisterRequest;
import com.descenedigital.model.Role;
import com.descenedigital.model.User;
import com.descenedigital.repo.UserRepository;
import com.descenedigital.security.CustomUserDetailsService;
import com.descenedigital.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userDetailsService;

    public Response<?> register(RegisterRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            return Response.builder()
                    .success(false)
                    .message("Username already taken")
                    .build();
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER.name())
                .build();
        userRepo.save(user);

        return  Response.builder()
                .success(true)
                .message("User registered successfully")
                .build();
    }

    public Response<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails);

            return Response.builder()
                    .success(true)
                    .message("User Login Successfully")
                    .data(token).build();
        } catch (BadCredentialsException e) {
            return Response.builder()
                    .success(false)
                    .message("Invalid username or password")
                    .build();

        }
    }

}
