package com.descenedigital;

import com.descenedigital.model.User;
import com.descenedigital.model.Role;
import com.descenedigital.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class AdviceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdviceApiApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("123456"));
				admin.setRole(Role.ADMIN.name());
				userRepository.save(admin);
				System.out.println("Admin user created!");
			}
		};
	}
}
