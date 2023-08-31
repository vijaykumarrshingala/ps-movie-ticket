package com.example.arv.user.utils;

import com.example.arv.user.entities.User;
import com.example.arv.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserDataLoader implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Load sample user data
        User user1 = User.builder()
                .username("vijaypatel")
                .password("vijaypatel")
                .email("vijaypatel@example.com")
                .role("ROLE_USER")
                .build();

        User adminUser = User.builder()
                .username("dhoni")
                .password("dhoni")
                .email("dhoni@example.com")
                .role("ROLE_ADMIN")
                .build();
        userRepository.saveAll(Arrays.asList(user1, adminUser));
    }
}
