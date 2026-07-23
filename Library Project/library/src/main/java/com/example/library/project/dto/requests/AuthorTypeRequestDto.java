package com.example.library.project.dto.requests;

import com.example.library.project.model.enums.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorTypeRequestDto {

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

    public AuthorTypeRequestDto() {
    }

    public AuthorTypeRequestDto(Long authorTypeId, ArtExpertise artExpertise, AuthorExpertise authorExpertise, AuthorTypeRole authorTypeRole, AuthorWritingStyle authorWritingStyle, EngineeringExpertise engineeringExpertise, HumanitiesExpertise humanitiesExpertise, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public AuthorTypeRequestDto setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
        return this;
    }

    public ArtExpertise getArtExpertise() {
        return artExpertise;
    }

    public AuthorTypeRequestDto setArtExpertise(ArtExpertise artExpertise) {
        this.artExpertise = artExpertise;
        return this;
    }

    public AuthorExpertise getAuthorExpertise() {
        return authorExpertise;
    }

    public AuthorTypeRequestDto setAuthorExpertise(AuthorExpertise authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public AuthorTypeRole getAuthorTypeRole() {
        return authorTypeRole;
    }

    public AuthorTypeRequestDto setAuthorTypeRole(AuthorTypeRole authorTypeRole) {
        this.authorTypeRole = authorTypeRole;
        return this;
    }

    public AuthorWritingStyle getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public AuthorTypeRequestDto setAuthorWritingStyle(AuthorWritingStyle authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public EngineeringExpertise getEngineeringExpertise() {
        return engineeringExpertise;
    }

    public AuthorTypeRequestDto setEngineeringExpertise(EngineeringExpertise engineeringExpertise) {
        this.engineeringExpertise = engineeringExpertise;
        return this;
    }

    public HumanitiesExpertise getHumanitiesExpertise() {
        return humanitiesExpertise;
    }

    public AuthorTypeRequestDto setHumanitiesExpertise(HumanitiesExpertise humanitiesExpertise) {
        this.humanitiesExpertise = humanitiesExpertise;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public AuthorTypeRequestDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorTypeRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorTypeRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorTypeRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}