package com.example.library;

import com.example.library.project.dto.responses.UserResponseDto;
import com.example.library.project.services.interfaces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class LibraryApplication implements CommandLineRunner {

    public static UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new  BCryptPasswordEncoder();

    public LibraryApplication(UserService userService) {
        LibraryApplication.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<UserResponseDto> findAllUsers = userService.findAll();

        findAllUsers
                .stream()
                .forEach((user ->
                {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }));

        System.out.println(findAllUsers);
    }

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }
}