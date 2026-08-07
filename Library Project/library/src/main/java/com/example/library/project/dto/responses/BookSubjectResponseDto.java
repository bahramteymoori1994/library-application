package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.BookSubject;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookSubjectResponseDto {

    private Long bookSubjectId;
    private String subjectTitle;
    private BookSubject bookSubject;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public BookSubjectResponseDto() {
    }

    public BookSubjectResponseDto(Long bookSubjectId, String subjectTitle, BookSubject bookSubject, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookSubjectId = bookSubjectId;
        this.subjectTitle = subjectTitle;
        this.bookSubject = bookSubject;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getBookSubjectId() {
        return bookSubjectId;
    }

    public BookSubjectResponseDto setBookSubjectId(Long bookSubjectId) {
        this.bookSubjectId = bookSubjectId;
        return this;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public BookSubjectResponseDto setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
        return this;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public BookSubjectResponseDto setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookSubjectResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookSubjectResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookSubjectResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}