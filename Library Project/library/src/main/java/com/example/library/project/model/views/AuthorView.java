package com.example.library.project.model.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Immutable
@Table(name = "author_view")
public class AuthorView {
    @Id
    @NotNull
    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @NotNull
    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Size(max = 50)
    @NotNull
    @Column(name = "author_first_name", nullable = false, length = 50)
    private String authorFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "author_last_name", nullable = false, length = 50)
    private String authorLastName;

    @Size(max = 10)
    @NotNull
    @Column(name = "author_national_code", nullable = false, length = 10)
    private String authorNationalCode;

    @NotNull
    @Column(name = "author_birth_date", nullable = false)
    private LocalDate authorBirthDate;

    @Column(name = "author_type_id")
    private Long authorTypeId;

    @Lob
    @Column(name = "author_type_role")
    private String authorTypeRole;

    @Lob
    @Column(name = "author_expertise")
    private String authorExpertise;

    @Lob
    @Column(name = "author_art_expertise")
    private String authorArtExpertise;

    @Lob
    @Column(name = "author_writing_style")
    private String authorWritingStyle;

    @Lob
    @Column(name = "author_engineering_expertise")
    private String authorEngineeringExpertise;

    @Lob
    @Column(name = "author_historical_period_level")
    private String authorHistoricalPeriodLevel;

    @Lob
    @Column(name = "author_humanities_expertise")
    private String authorHumanitiesExpertise;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "created_time", nullable = false)
    private LocalTime createdTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    public AuthorView() {
    }

    public AuthorView(Long authorId, Long personId, String authorFirstName, String authorLastName, String authorNationalCode, LocalDate authorBirthDate, Long authorTypeId, String authorTypeRole, String authorExpertise, String authorArtExpertise, String authorWritingStyle, String authorEngineeringExpertise, String authorHistoricalPeriodLevel, String authorHumanitiesExpertise, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public AuthorView setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Long getPersonId() {
        return personId;
    }

    public AuthorView setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public AuthorView setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public AuthorView setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public String getAuthorNationalCode() {
        return authorNationalCode;
    }

    public AuthorView setAuthorNationalCode(String authorNationalCode) {
        this.authorNationalCode = authorNationalCode;
        return this;
    }

    public LocalDate getAuthorBirthDate() {
        return authorBirthDate;
    }

    public AuthorView setAuthorBirthDate(LocalDate authorBirthDate) {
        this.authorBirthDate = authorBirthDate;
        return this;
    }

    public Long getAuthorTypeId() {
        return authorTypeId;
    }

    public AuthorView setAuthorTypeId(Long authorTypeId) {
        this.authorTypeId = authorTypeId;
        return this;
    }

    public String getAuthorTypeRole() {
        return authorTypeRole;
    }

    public AuthorView setAuthorTypeRole(String authorTypeRole) {
        this.authorTypeRole = authorTypeRole;
        return this;
    }

    public String getAuthorExpertise() {
        return authorExpertise;
    }

    public AuthorView setAuthorExpertise(String authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public String getAuthorArtExpertise() {
        return authorArtExpertise;
    }

    public AuthorView setAuthorArtExpertise(String authorArtExpertise) {
        this.authorArtExpertise = authorArtExpertise;
        return this;
    }

    public String getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public AuthorView setAuthorWritingStyle(String authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public String getAuthorEngineeringExpertise() {
        return authorEngineeringExpertise;
    }

    public AuthorView setAuthorEngineeringExpertise(String authorEngineeringExpertise) {
        this.authorEngineeringExpertise = authorEngineeringExpertise;
        return this;
    }

    public String getAuthorHistoricalPeriodLevel() {
        return authorHistoricalPeriodLevel;
    }

    public AuthorView setAuthorHistoricalPeriodLevel(String authorHistoricalPeriodLevel) {
        this.authorHistoricalPeriodLevel = authorHistoricalPeriodLevel;
        return this;
    }

    public String getAuthorHumanitiesExpertise() {
        return authorHumanitiesExpertise;
    }

    public AuthorView setAuthorHumanitiesExpertise(String authorHumanitiesExpertise) {
        this.authorHumanitiesExpertise = authorHumanitiesExpertise;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorView setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorView setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorView setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "AuthorView{" +
                "authorId=" + authorId +
                ", personId=" + personId +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", authorNationalCode='" + authorNationalCode + '\'' +
                ", authorBirthDate=" + authorBirthDate +
                ", authorTypeId=" + authorTypeId +
                ", authorTypeRole='" + authorTypeRole + '\'' +
                ", authorExpertise='" + authorExpertise + '\'' +
                ", authorArtExpertise='" + authorArtExpertise + '\'' +
                ", authorWritingStyle='" + authorWritingStyle + '\'' +
                ", authorEngineeringExpertise='" + authorEngineeringExpertise + '\'' +
                ", authorHistoricalPeriodLevel='" + authorHistoricalPeriodLevel + '\'' +
                ", authorHumanitiesExpertise='" + authorHumanitiesExpertise + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}