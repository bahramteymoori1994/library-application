package com.example.library.project.model.entities;

import com.example.library.project.model.enums.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "authorTypeEntity")
@Table(name = "author-type")
public class AuthorType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_TYPE_ID")
    private Long authorTypeId;

    @Column(name = "ART_EXPERTISE")
    @Enumerated(value = EnumType.STRING)
    private ArtExpertise artExpertise;

    @Column(name = "AUTHOR_EXPERTISE")
    @Enumerated(value = EnumType.STRING)
    private AuthorExpertise authorExpertise;

    @Column(name = "AUTHOR_TYPE_ROLE")
    @Enumerated(value = EnumType.STRING)
    private AuthorTypeRole authorTypeRole;

    @Column(name = "AUTHOR_WRITING_STYLE")
    @Enumerated(value = EnumType.STRING)
    private AuthorWritingStyle authorWritingStyle;

    @Column(name = "ENGINEERING_EXPERTISE")
    @Enumerated(value = EnumType.STRING)
    private EngineeringExpertise engineeringExpertise;

    @Column(name = "HUMANITIES_EXPERTISE")
    @Enumerated(value = EnumType.STRING)
    private HumanitiesExpertise humanitiesExpertise;

    @Column(name = "HISTORICAL_PERIOD_LEVEL")
    @Enumerated(value = EnumType.STRING)
    private HistoricalPeriodLevel historicalPeriodLevel;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public AuthorType() {
    }

    public AuthorType(Long authorTypeId, ArtExpertise artExpertise, AuthorExpertise authorExpertise, AuthorTypeRole authorTypeRole, AuthorWritingStyle authorWritingStyle, EngineeringExpertise engineeringExpertise, HumanitiesExpertise humanitiesExpertise, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public AuthorType setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
        return this;
    }

    public ArtExpertise getArtExpertise() {
        return artExpertise;
    }

    public AuthorType setArtExpertise(ArtExpertise artExpertise) {
        this.artExpertise = artExpertise;
        return this;
    }

    public AuthorExpertise getAuthorExpertise() {
        return authorExpertise;
    }

    public AuthorType setAuthorExpertise(AuthorExpertise authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public AuthorTypeRole getAuthorTypeRole() {
        return authorTypeRole;
    }

    public AuthorType setAuthorTypeRole(AuthorTypeRole authorTypeRole) {
        this.authorTypeRole = authorTypeRole;
        return this;
    }

    public AuthorWritingStyle getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public AuthorType setAuthorWritingStyle(AuthorWritingStyle authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public EngineeringExpertise getEngineeringExpertise() {
        return engineeringExpertise;
    }

    public AuthorType setEngineeringExpertise(EngineeringExpertise engineeringExpertise) {
        this.engineeringExpertise = engineeringExpertise;
        return this;
    }

    public HumanitiesExpertise getHumanitiesExpertise() {
        return humanitiesExpertise;
    }

    public AuthorType setHumanitiesExpertise(HumanitiesExpertise humanitiesExpertise) {
        this.humanitiesExpertise = humanitiesExpertise;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public AuthorType setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorType setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorType setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorType setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorType{" +
                "authorTypeId=" + authorTypeId +
                ", artExpertise=" + artExpertise +
                ", authorExpertise=" + authorExpertise +
                ", authorTypeRole=" + authorTypeRole +
                ", authorWritingStyle=" + authorWritingStyle +
                ", engineeringExpertise=" + engineeringExpertise +
                ", humanitiesExpertise=" + humanitiesExpertise +
                ", historicalPeriodLevel=" + historicalPeriodLevel +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}