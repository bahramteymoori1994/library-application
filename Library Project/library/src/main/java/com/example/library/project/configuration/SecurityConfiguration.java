package com.example.library.project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers("/css/**", "/js/**", "/login").permitAll()
                                .anyRequest().authenticated())
                .formLogin(login ->
                        login
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/welcome", true)
                                .failureUrl("/login?error=true")
                                .permitAll())
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout=true")
                                .permitAll())
                .build();
    }

//    @Bean
//    public SessionRepository<? extends Session> sessionRepository(RedisOperations<String, Object>  redisOperations)
//    {
//        return new RedisIndexedSessionRepository(redisOperations);
//    }
}