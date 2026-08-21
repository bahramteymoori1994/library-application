package com.example.library.project.configuration;

import com.example.library.project.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserServiceImpl userService;

    public SecurityConfiguration(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                            .requestMatchers("/welcome").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/book/**").hasAnyRole("USER", "ADMIN")
                            .requestMatchers("/author/**",
                                    "/authorType/**",
                                    "/bookSubject/**",
                                    "/bookType/**",
                                    "/library/**",
                                    "/libraryType/**",
                                    "/person/**",
                                    "/publisher/**",
                                    "/publisherType/**",
                                    "/role/**",
                                    "/receipt/**",
                                    "/user/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .formLogin(form ->
                {
                    form
                            .defaultSuccessUrl("/welcome");
                })
                .authenticationProvider(authenticationProvider())
                .logout(logout ->
                {
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login?logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll();
                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService);
        authProvider.setPasswordEncoder(getBCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}