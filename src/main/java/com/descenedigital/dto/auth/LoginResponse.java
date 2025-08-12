package com.descenedigital.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class LoginResponse {
    private String role;
    private String token;
}