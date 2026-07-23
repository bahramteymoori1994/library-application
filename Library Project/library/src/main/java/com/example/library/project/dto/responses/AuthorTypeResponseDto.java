package com.example.library.project.dto.responses;

import com.example.library.project.model.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorTypeResponseDto {

    private Long authorTypeId;
    private ArtExpertise artExpertise;
    private AuthorExpertise authorExpertise;
    private AuthorTypeRole authorTypeRole;
    private AuthorWritingStyle authorWritingStyle;
    private EngineeringExpertise engineeringExpertise;
    private HumanitiesExpertise humanitiesExpertise;
    private HistoricalPeriodLevel historicalPeriodLevel;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorTypeResponseDto() {
    }

    public AuthorTypeResponseDto(Long authorTypeId, ArtExpertise artExpertise, AuthorExpertise authorExpertise, AuthorTypeRole authorTypeRole, AuthorWritingStyle authorWritingStyle, EngineeringExpertise engineeringExpertise, HumanitiesExpertise humanitiesExpertise, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorTypeId = authorTypeId;
        this.artExpertise = artExpertise;
        this.authorExpertise = authorExpertise;
        this.authorTypeRole = authorTypeRole;
        this.authorWritingStyle = authorWritingStyle;
        this.engineeringExpertise = engineeringExpertise;
        this.humanitiesExpertise = humanitiesExpertise;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getAuthorTypeId() {
        return authorTypeId;
    }

    public AuthorTypeResponseDto setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
        return this;
    }

    public ArtExpertise getArtExpertise() {
        return artExpertise;
    }

    public AuthorTypeResponseDto setArtExpertise(ArtExpertise artExpertise) {
        this.artExpertise = artExpertise;
        return this;
    }

    public AuthorExpertise getAuthorExpertise() {
        return authorExpertise;
    }

    public AuthorTypeResponseDto setAuthorExpertise(AuthorExpertise authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public AuthorTypeRole getAuthorTypeRole() {
        return authorTypeRole;
    }

    public AuthorTypeResponseDto setAuthorTypeRole(AuthorTypeRole authorTypeRole) {
        this.authorTypeRole = authorTypeRole;
        return this;
    }

    public AuthorWritingStyle getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public AuthorTypeResponseDto setAuthorWritingStyle(AuthorWritingStyle authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public EngineeringExpertise getEngineeringExpertise() {
        return engineeringExpertise;
    }

    public AuthorTypeResponseDto setEngineeringExpertise(EngineeringExpertise engineeringExpertise) {
        this.engineeringExpertise = engineeringExpertise;
        return this;
    }

    public HumanitiesExpertise getHumanitiesExpertise() {
        return humanitiesExpertise;
    }

    public AuthorTypeResponseDto setHumanitiesExpertise(HumanitiesExpertise humanitiesExpertise) {
        this.humanitiesExpertise = humanitiesExpertise;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public AuthorTypeResponseDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorTypeResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorTypeResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorTypeResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}