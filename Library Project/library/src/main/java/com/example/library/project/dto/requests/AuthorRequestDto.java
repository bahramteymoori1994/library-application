package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.AuthorType;
import com.example.library.project.model.entities.Person;
import java.time.LocalDate;
import java.time.LocalTime;

public class AuthorRequestDto {

    private Long authorId;
    private Person person;
    private AuthorType authorType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public AuthorRequestDto() {
    }

    public AuthorRequestDto(Long authorId, Person person, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public AuthorRequestDto setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public AuthorRequestDto setPerson(Person person) {
        this.person = person;
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