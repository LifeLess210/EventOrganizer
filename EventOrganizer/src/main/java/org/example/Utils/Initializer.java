package org.example.Utils;

import jakarta.annotation.PostConstruct;
import org.example.DTOs.UserDTO;
import org.example.Repositories.UserRepository;
import org.example.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class Initializer implements CommandLineRunner {
    private final UserService userService;

    public Initializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        if (Objects.isNull(userService.getUserById(1L))) {
            UserDTO user = UserDTO.builder()
                    .email("asd@example.com")
                    .userName("asd")
                    .role("ADMIN")
                    .password("asd")
                    .build();
            userService.createUser(user);
            System.out.println("Admin user created: " + user);
        } else {
            System.out.println("Admin user already exists.");
        }
    }

}
