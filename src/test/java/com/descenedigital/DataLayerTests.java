package com.descenedigital;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.descenedigital.model.User;
import com.descenedigital.repo.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DataLayerTests {

    @Mock
    private UserRepository repository;

    private static User validUser;

    @BeforeAll
    static void setup() {
        validUser = User.builder()
                .username("Mariam Mohammed")
                .email("mariam.mohammed@gmail.com")
                .password("encodedPassword")
                .role("USER")
                .build();
    }

    @Test
    public void testInsertUser() {
        when(repository.save(validUser)).thenReturn(validUser);

        User savedUser = repository.save(validUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("Mariam Mohammed");
    }

    @Test
    public void testFindUserByUsername() {
        when(repository.findByUsername("Mariam Mohammed")).thenReturn(Optional.of(validUser));

        Optional<User> foundUser = repository.findByUsername("Mariam Mohammed");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("mariam.mohammed@gmail.com");
    }

    @Test
    public void testFindUserByUsername_NotFound() {
        when(repository.findByUsername("NonExistingUser")).thenReturn(Optional.empty());

        Optional<User> foundUser = repository.findByUsername("NonExistingUser");

        assertThat(foundUser).isNotPresent();
    }
}
