package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.AuthorType;
import com.example.library.project.model.entities.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorResponseDto {

    private Long authorId;
    private String firstName;
    private String lastName;
    private AuthorType authorType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorResponseDto() {
    }

    public AuthorResponseDto(Long authorId, String firstName, String lastName, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorType = authorType;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public AuthorResponseDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorResponseDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorResponseDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AuthorType getAuthorType() {
        return authorType;
    }

    public AuthorResponseDto setAuthorType(AuthorType authorType) {
        this.authorType = authorType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}