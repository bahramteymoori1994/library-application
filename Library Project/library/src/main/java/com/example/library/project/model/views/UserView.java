package com.example.library.project.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Immutable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Entity
@Immutable
@Table(name = "user_view", schema = "test")
public class UserView {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "person_first_name")
    private String personFirstName;

    @Column(name = "person_last_name")
    private String personLastName;

    @Column(name = "person_national_code")
    private String personNationalCode;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "user_created_date")
    private LocalDate userCreatedDate;

    @Column(name = "user_created_time")
    private LocalTime userCreatedTime;

    @Column(name = "user_created_by")
    private String userCreatedBy;

    @Column(name = "roles_farsi")
    private String rolesFarsi;

//    @Column(name = "role_ids")
//    private List<Long> roleIds;
//
//    @Column(name = "roles_list")
//    private List<String> rolesList;

    public UserView() {
    }

    public UserView(Long userId, Long personId, String personFirstName, String personLastName, String personNationalCode, String userName, String email, LocalDate userCreatedDate, LocalTime userCreatedTime, String userCreatedBy, String rolesFarsi) {
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

    public Long getUserId() {
        return userId;
    }

    public UserView setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public UserView setPersonId(Long personId) {
        this.personId = personId;
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

    public String getEmail() {
        return email;
    }

    public UserView setEmail(String email) {
        this.email = email;
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

    public String getRolesFarsi() {
        return rolesFarsi;
    }

    public UserView setRolesFarsi(String rolesFarsi) {
        this.rolesFarsi = rolesFarsi;
        return this;
    }
}