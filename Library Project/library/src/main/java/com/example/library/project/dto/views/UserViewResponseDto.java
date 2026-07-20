package com.example.library.project.dto.views;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserViewResponseDto {

    private Long userId;
    private Long personId;
    private String personFirstName;
    private String personLastName;
    private String personNationalCode;
    private String userName;
    private String userEmail;
    private LocalDate userCreatedDate;
    private LocalTime userCreatedTime;
    private String userCreatedBy;

    public Long getUserId() {
        return userId;
    }

    public UserViewResponseDto setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public UserViewResponseDto setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public Long getPersonId() {
        return personId;
    }

    public UserViewResponseDto setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }
}