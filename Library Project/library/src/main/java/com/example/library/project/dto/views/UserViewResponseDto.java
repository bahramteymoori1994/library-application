package com.example.library.project.dto.views;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UserViewResponseDto {

    private Long userId;
    private Long personId;
    private String personFirstName;
    private String personLastName;
    private String personNationalCode;
    private String userName;
    private String email;
    private LocalDate userCreatedDate;
    private LocalTime userCreatedTime;
    private String userCreatedBy;
    private String rolesFarsi;

    // اضافه کردن فیلدهای کمکی برای نمایش در ویو
    private List<String> rolesList = new ArrayList<>();
    private List<Long> roleIds = new ArrayList<>();

    public UserViewResponseDto() {
    }

    public UserViewResponseDto(Long userId, Long personId, String personFirstName, String personLastName,
                               String personNationalCode, String userName, String email,
                               LocalDate userCreatedDate, LocalTime userCreatedTime,
                               String userCreatedBy, String rolesFarsi) {
        this.userId = userId;
        this.personId = personId;
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.personNationalCode = personNationalCode;
        this.userName = userName;
        this.email = email;
        this.userCreatedDate = userCreatedDate;
        this.userCreatedTime = userCreatedTime;
        this.userCreatedBy = userCreatedBy;
        this.rolesFarsi = rolesFarsi;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public UserViewResponseDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public UserViewResponseDto setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public UserViewResponseDto setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public UserViewResponseDto setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public String getPersonNationalCode() {
        return personNationalCode;
    }

    public UserViewResponseDto setPersonNationalCode(String personNationalCode) {
        this.personNationalCode = personNationalCode;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserViewResponseDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewResponseDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getUserCreatedDate() {
        return userCreatedDate;
    }

    public UserViewResponseDto setUserCreatedDate(LocalDate userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
        return this;
    }

    public LocalTime getUserCreatedTime() {
        return userCreatedTime;
    }

    public UserViewResponseDto setUserCreatedTime(LocalTime userCreatedTime) {
        this.userCreatedTime = userCreatedTime;
        return this;
    }

    public String getUserCreatedBy() {
        return userCreatedBy;
    }

    public UserViewResponseDto setUserCreatedBy(String userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
        return this;
    }

    public String getRolesFarsi() {
        return rolesFarsi;
    }

    public UserViewResponseDto setRolesFarsi(String rolesFarsi) {
        this.rolesFarsi = rolesFarsi;
        return this;
    }

    public List<String> getRolesList() {
        return rolesList;
    }

    public UserViewResponseDto setRolesList(List<String> rolesList) {
        this.rolesList = rolesList;
        return this;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public UserViewResponseDto setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

    @Override
    public String toString() {
        return "UserViewResponseDto{" +
                "userId=" + userId +
                ", personId=" + personId +
                ", personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", personNationalCode='" + personNationalCode + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userCreatedDate=" + userCreatedDate +
                ", userCreatedTime=" + userCreatedTime +
                ", userCreatedBy='" + userCreatedBy + '\'' +
                ", rolesFarsi='" + rolesFarsi + '\'' +
                ", rolesList=" + rolesList +
                ", roleIds=" + roleIds +
                '}';
    }
}