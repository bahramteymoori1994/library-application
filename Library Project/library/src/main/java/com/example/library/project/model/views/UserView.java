package com.example.library.project.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Immutable
@Table(name = "user_view", schema = "test")
public class UserView {
    @Id
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "person_id", nullable = false)
    @NotNull
    private Long personId;

    @Size(max = 50)
    @NotNull
    @Column(name = "person_first_name", nullable = false, length = 50)
    private String personFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "person_last_name", nullable = false, length = 50)
    private String personLastName;

    @Size(max = 10)
    @NotNull
    @Column(name = "person_national_code", nullable = false, length = 10)
    private String personNationalCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Size(max = 100)
    @NotNull
    @Column(name = "user_email", nullable = false, length = 100)
    private String userEmail;

    @NotNull
    @Column(name = "user_created_date", nullable = false)
    private LocalDate userCreatedDate;

    @NotNull
    @Column(name = "user_created_time", nullable = false)
    private LocalTime userCreatedTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "user_created_by", nullable = false, length = 50)
    private String userCreatedBy;

    public Long getUserId() {
        return userId;
    }

    public UserView setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public UserView setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
        return this;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public UserView setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
        return this;
    }

    public String getPersonNationalCode() {
        return personNationalCode;
    }

    public UserView setPersonNationalCode(String personNationalCode) {
        this.personNationalCode = personNationalCode;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserView setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public UserView setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public LocalDate getUserCreatedDate() {
        return userCreatedDate;
    }

    public UserView setUserCreatedDate(LocalDate userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
        return this;
    }

    public LocalTime getUserCreatedTime() {
        return userCreatedTime;
    }

    public UserView setUserCreatedTime(LocalTime userCreatedTime) {
        this.userCreatedTime = userCreatedTime;
        return this;
    }

    public String getUserCreatedBy() {
        return userCreatedBy;
    }

    public UserView setUserCreatedBy(String userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public UserView setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }
}