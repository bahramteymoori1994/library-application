package com.example.library.project.dto.responses;

import java.time.LocalDate;
import java.time.LocalTime;

public class RoleResponseDto {

    private Long roleId;
    private String englishRoleTitle;
    private String farsiRoleTitle;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public RoleResponseDto() {
    }

    public RoleResponseDto(Long roleId, String englishRoleTitle, String farsiRoleTitle, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public RoleResponseDto setRoleId(Long roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getEnglishRoleTitle() {
        return englishRoleTitle;
    }

    public RoleResponseDto setEnglishRoleTitle(String englishRoleTitle) {
        this.englishRoleTitle = englishRoleTitle;
        return this;
    }

    public String getFarsiRoleTitle() {
        return farsiRoleTitle;
    }

    public RoleResponseDto setFarsiRoleTitle(String farsiRoleTitle) {
        this.farsiRoleTitle = farsiRoleTitle;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public RoleResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public RoleResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public RoleResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "RoleResponseDto{" +
                "roleId=" + roleId +
                ", englishRoleTitle='" + englishRoleTitle + '\'' +
                ", farsiRoleTitle='" + farsiRoleTitle + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}