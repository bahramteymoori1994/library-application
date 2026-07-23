package com.example.library.project.dto.responses;

import com.example.library.project.model.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookTypeResponseDto {

    private Long bookTypeId;
    private BookTypeSubject bookTypeSubject;
    private BookTypeAgeGroup bookTypeAgeGroup;
    private BookTypeLanguage bookTypeLanguage;
    private BookTypePublicationStatus bookTypePublicationStatus;
    private BookAcademicLevel bookAcademicLevel;
    private HistoricalPeriodLevel historicalPeriodLevel;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public BookTypeResponseDto() {
    }

    public BookTypeResponseDto(Long bookTypeId, BookTypeSubject bookTypeSubject, BookTypeAgeGroup bookTypeAgeGroup, BookTypeLanguage bookTypeLanguage, BookTypePublicationStatus bookTypePublicationStatus, BookAcademicLevel bookAcademicLevel, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookTypeId = bookTypeId;
        this.bookTypeSubject = bookTypeSubject;
        this.bookTypeAgeGroup = bookTypeAgeGroup;
        this.bookTypeLanguage = bookTypeLanguage;
        this.bookTypePublicationStatus = bookTypePublicationStatus;
        this.bookAcademicLevel = bookAcademicLevel;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getBookTypeId() {
        return bookTypeId;
    }

    public BookTypeResponseDto setBookTypeId(Long bookTypeId) {
        this.bookTypeId = bookTypeId;
        return this;
    }

    public BookTypeSubject getBookTypeSubject() {
        return bookTypeSubject;
    }

    public BookTypeResponseDto setBookTypeSubject(BookTypeSubject bookTypeSubject) {
        this.bookTypeSubject = bookTypeSubject;
        return this;
    }

    public BookTypeAgeGroup getBookTypeAgeGroup() {
        return bookTypeAgeGroup;
    }

    public BookTypeResponseDto setBookTypeAgeGroup(BookTypeAgeGroup bookTypeAgeGroup) {
        this.bookTypeAgeGroup = bookTypeAgeGroup;
        return this;
    }

    public BookTypeLanguage getBookTypeLanguage() {
        return bookTypeLanguage;
    }

    public BookTypeResponseDto setBookTypeLanguage(BookTypeLanguage bookTypeLanguage) {
        this.bookTypeLanguage = bookTypeLanguage;
        return this;
    }

    public BookTypePublicationStatus getBookTypePublicationStatus() {
        return bookTypePublicationStatus;
    }

    public BookTypeResponseDto setBookTypePublicationStatus(BookTypePublicationStatus bookTypePublicationStatus) {
        this.bookTypePublicationStatus = bookTypePublicationStatus;
        return this;
    }

    public BookAcademicLevel getBookAcademicLevel() {
        return bookAcademicLevel;
    }

    public BookTypeResponseDto setBookAcademicLevel(BookAcademicLevel bookAcademicLevel) {
        this.bookAcademicLevel = bookAcademicLevel;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public BookTypeResponseDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookTypeResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookTypeResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookTypeResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}