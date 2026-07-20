package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.Person;
import com.example.library.project.model.entities.Role;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserResponseDto {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;
    private Person person;
    private List<Role> roles = new ArrayList<>();

    public UserResponseDto() {
    }

    public UserResponseDto(Long userId, String username, String password, String email, LocalDate createdDate, LocalTime createdTime, String createdBy, Person person, List<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.person = person;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public UserResponseDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResponseDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public UserResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public UserResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public UserResponseDto setPerson(Person person) {
        this.person = person;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserResponseDto setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", person=" + person +
                ", roles=" + roles +
                '}';
    }
}