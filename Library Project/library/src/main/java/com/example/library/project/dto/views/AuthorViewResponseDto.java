package com.example.library.project.dto.views;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorViewResponseDto {

    private Long authorId;
    private Long personId;
    private String authorFirstName;
    private String authorLastName;
    private String authorNationalCode;
    private LocalDate authorBirthDate;
    private Long authorTypeId;
    private String authorTypeRole;
    private String authorExpertise;
    private String authorArtExpertise;
    private String authorWritingStyle;
    private String authorEngineeringExpertise;
    private String authorHistoricalPeriodLevel;
    private String authorHumanitiesExpertise;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorViewResponseDto() {
    }

    public AuthorViewResponseDto(Long authorId, Long personId, String authorFirstName, String authorLastName, String authorNationalCode, LocalDate authorBirthDate, Long authorTypeId, String authorTypeRole, String authorExpertise, String authorArtExpertise, String authorWritingStyle, String authorEngineeringExpertise, String authorHistoricalPeriodLevel, String authorHumanitiesExpertise, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorId = authorId;
        this.personId = personId;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.authorNationalCode = authorNationalCode;
        this.authorBirthDate = authorBirthDate;
        this.authorTypeId = authorTypeId;
        this.authorTypeRole = authorTypeRole;
        this.authorExpertise = authorExpertise;
        this.authorArtExpertise = authorArtExpertise;
        this.authorWritingStyle = authorWritingStyle;
        this.authorEngineeringExpertise = authorEngineeringExpertise;
        this.authorHistoricalPeriodLevel = authorHistoricalPeriodLevel;
        this.authorHumanitiesExpertise = authorHumanitiesExpertise;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public AuthorViewResponseDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public AuthorViewResponseDto setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public AuthorViewResponseDto setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public AuthorViewResponseDto setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public String getAuthorNationalCode() {
        return authorNationalCode;
    }

    public AuthorViewResponseDto setAuthorNationalCode(String authorNationalCode) {
        this.authorNationalCode = authorNationalCode;
        return this;
    }

    public LocalDate getAuthorBirthDate() {
        return authorBirthDate;
    }

    public AuthorViewResponseDto setAuthorBirthDate(LocalDate authorBirthDate) {
        this.authorBirthDate = authorBirthDate;
        return this;
    }

    public Long getAuthorTypeId() {
        return authorTypeId;
    }

    public AuthorViewResponseDto setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
        return this;
    }

    public String getAuthorTypeRole() {
        return authorTypeRole;
    }

    public AuthorViewResponseDto setAuthorTypeRole(String authorTypeRole) {
        this.authorTypeRole = authorTypeRole;
        return this;
    }

    public String getAuthorExpertise() {
        return authorExpertise;
    }

    public AuthorViewResponseDto setAuthorExpertise(String authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public String getAuthorArtExpertise() {
        return authorArtExpertise;
    }

    public AuthorViewResponseDto setAuthorArtExpertise(String authorArtExpertise) {
        this.authorArtExpertise = authorArtExpertise;
        return this;
    }

    public String getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public AuthorViewResponseDto setAuthorWritingStyle(String authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public String getAuthorEngineeringExpertise() {
        return authorEngineeringExpertise;
    }

    public AuthorViewResponseDto setAuthorEngineeringExpertise(String authorEngineeringExpertise) {
        this.authorEngineeringExpertise = authorEngineeringExpertise;
        return this;
    }

    public String getAuthorHistoricalPeriodLevel() {
        return authorHistoricalPeriodLevel;
    }

    public AuthorViewResponseDto setAuthorHistoricalPeriodLevel(String authorHistoricalPeriodLevel) {
        this.authorHistoricalPeriodLevel = authorHistoricalPeriodLevel;
        return this;
    }

    public String getAuthorHumanitiesExpertise() {
        return authorHumanitiesExpertise;
    }

    public AuthorViewResponseDto setAuthorHumanitiesExpertise(String authorHumanitiesExpertise) {
        this.authorHumanitiesExpertise = authorHumanitiesExpertise;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorViewResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorViewResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorViewResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}