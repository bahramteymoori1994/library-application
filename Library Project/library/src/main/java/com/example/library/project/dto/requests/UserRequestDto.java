package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.Person;
import com.example.library.project.model.entities.Role;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserRequestDto {

    private Long userId;
    private String username;
    private String password;
    private String email;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;
    private Person person;

    // تغییر: استفاده از List<Long> برای دریافت ID نقش‌ها
    private List<Long> roleIds = new ArrayList<>();

    // فیلد برای نمایش در ویو
    private List<Role> roles = new ArrayList<>();

    public UserRequestDto() {
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public UserRequestDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRequestDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRequestDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRequestDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public UserRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public UserRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UserRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public UserRequestDto setPerson(Person person) {
        this.person = person;
        return this;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public UserRequestDto setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public UserRequestDto setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", person=" + person +
                ", roleIds=" + roleIds +
                ", roles=" + roles +
                '}';
    }
}