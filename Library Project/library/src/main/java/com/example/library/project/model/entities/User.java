package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "userEntity")
@Table(name = "user_person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NAME", columnDefinition = "varchar(50)", nullable = false, unique = true)
    @Length(min = 3, max = 50)
    @NotNull(message = "Username is required")
    private String username;

    @Column(name = "PASSWORD", columnDefinition = "varchar(200)", nullable = false)
    @Length(min = 3, max = 200)
    @NotNull(message = "Password is required")
    private String password;

    @Column(name = "EMAIL", columnDefinition = "varchar(100)", nullable = false, unique = true)
    @Email(message = "Email pattern must be match")
    @NotNull(message = "Email is required")
    private String email;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "date")
    @NotNull(message = "Created Date is required")
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", nullable = false, columnDefinition = "time")
    @NotNull(message = "Created Time is required")
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    @NotNull(message = "Created By is required")
    private String createdBy;

    @OneToOne
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "PERSON_ID")
    private Person person;

    public User() {
    }

    public User(Long userId, String username, String password, String email, LocalDate createdDate, LocalTime createdTime, String createdBy, Person person) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.person = person;
    }

    public Long getUserId() {
        return userId;
    }

    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public User setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public User setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public User setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public User setPerson(Person person) {
        this.person = person;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime='" + createdTime + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", person=" + person +
                '}';
    }
}