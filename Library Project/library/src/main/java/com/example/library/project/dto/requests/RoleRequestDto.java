package com.example.library.project.dto.requests;

import java.time.LocalDate;
import java.time.LocalTime;

public class RoleRequestDto {

    private Long roleId;
    private String englishRoleTitle;
    private String farsiRoleTitle;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public RoleRequestDto() {
    }

    public RoleRequestDto(Long roleId, String englishRoleTitle, String farsiRoleTitle, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public RoleRequestDto setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getEnglishRoleTitle() {
        return englishRoleTitle;
    }

    public RoleRequestDto setEnglishRoleTitle(String englishRoleTitle) {
        this.englishRoleTitle = englishRoleTitle;
        return this;
    }

    public String getFarsiRoleTitle() {
        return farsiRoleTitle;
    }

    public RoleRequestDto setFarsiRoleTitle(String farsiRoleTitle) {
        this.farsiRoleTitle = farsiRoleTitle;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public RoleRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public RoleRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RoleRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "RoleRequestDto{" +
                "roleId=" + roleId +
                ", englishRoleTitle='" + englishRoleTitle + '\'' +
                ", farsiRoleTitle='" + farsiRoleTitle + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}