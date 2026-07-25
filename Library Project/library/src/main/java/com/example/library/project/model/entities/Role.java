package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "roleEntity")
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "ENGLISH_ROLE_TITLE", columnDefinition = "varchar(100)", unique = true, nullable = false)
    @NotNull(message = "English role title is required")
    @Length(min = 3, max = 100)
    private String englishRoleTitle;

    @Column(name = "FARSI_ROLE_TITLE", columnDefinition = "nvarchar(100)", unique = true, nullable = false)
    @NotNull(message = "Farsi role title is required")
    @Length(min = 3, max = 100)
    private String farsiRoleTitle;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", columnDefinition = "varchar(100)", nullable = false)
    private String createdBy;

    public Role() {
    }

    public Role(Long roleId, String englishRoleTitle, String farsiRoleTitle, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.roleId = roleId;
        this.englishRoleTitle = englishRoleTitle;
        this.farsiRoleTitle = farsiRoleTitle;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Role setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getEnglishRoleTitle() {
        return englishRoleTitle;
    }

    public Role setEnglishRoleTitle(String englishRoleTitle) {
        this.englishRoleTitle = englishRoleTitle;
        return this;
    }

    public String getFarsiRoleTitle() {
        return farsiRoleTitle;
    }

    public Role setFarsiRoleTitle(String farsiRoleTitle) {
        this.farsiRoleTitle = farsiRoleTitle;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Role setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Role setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Role setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", englishRoleTitle='" + englishRoleTitle + '\'' +
                ", farsiRoleTitle='" + farsiRoleTitle + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}