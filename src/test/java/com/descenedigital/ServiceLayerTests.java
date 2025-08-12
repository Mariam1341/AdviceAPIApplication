package com.descenedigital;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.descenedigital.dto.Response;
import com.descenedigital.dto.register.RegisterRequest;
import com.descenedigital.model.User;
import com.descenedigital.repo.UserRepository;
import com.descenedigital.service.AuthService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;

//For demonstration purposes, I have implemented two unit/integration tests to illustrate the testing approach
@ExtendWith(MockitoExtension.class)
public class ServiceLayerTests {

    @Mock
    private UserRepository userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private static RegisterRequest validRegisterRequest;

    @BeforeAll
    static void init() {
        validRegisterRequest = new RegisterRequest();
        validRegisterRequest.setUsername("mariam");
        validRegisterRequest.setPassword("pass123");
        validRegisterRequest.setEmail("mariam.mohammed@example.com");
    }

    @BeforeEach
    void setupMocks() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPass");
    }

    @Test
    void testRegisterUser_Success() {
        when(userRepo.existsByUsername("mariam")).thenReturn(false);
        when(userRepo.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Response<?> response = authService.register(validRegisterRequest);

        assertTrue(response.isSuccess());
        assertEquals("User registered successfully", response.getMessage());

        User savedUser = (User) response.getData();
        assertEquals("mariam", savedUser.getUsername());
        assertEquals("encodedPass", savedUser.getPassword());
        assertEquals("USER", savedUser.getRole());

        verify(userRepo, times(1)).save(any(User.class));
        verify(passwordEncoder, times(1)).encode("pass123");
    }

    @Test
    void testRegisterUser_Failure_UsernameTaken() {
        when(userRepo.existsByUsername("mariam")).thenReturn(true);

        Response<?> response = authService.register(validRegisterRequest);

        assertFalse(response.isSuccess());
        assertEquals("Username already taken", response.getMessage());

        verify(userRepo, never()).save(any());
        verify(passwordEncoder, never()).encode(any());
    }


}
