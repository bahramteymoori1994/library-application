package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.AuthorType;
import com.example.library.project.model.entities.Person;

import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorResponseDto {

    private Long authorId;
    private Person person;
    private AuthorType authorType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorResponseDto() {
    }

    public AuthorResponseDto(Long authorId, Person person, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorId = authorId;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public AuthorResponseDto setPerson(Person person) {
        this.person = person;
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