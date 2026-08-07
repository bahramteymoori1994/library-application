package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.AuthorType;
import com.example.library.project.model.entities.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorRequestDto {

    private Long authorId;
    private String firstName;
    private String lastName;
    private AuthorType authorType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorRequestDto() {
    }

    public AuthorRequestDto(Long authorId, String firstName, String lastName, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public AuthorRequestDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AuthorRequestDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AuthorRequestDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AuthorType getAuthorType() {
        return authorType;
    }

    public AuthorRequestDto setAuthorType(AuthorType authorType) {
        this.authorType = authorType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public AuthorRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public AuthorRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public AuthorRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}