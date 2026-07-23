package com.example.library.project.model.entities;

import com.example.library.project.model.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "bookTypeEntity")
@Table(name = "book_type")
public class BookType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_TYPE_ID")
    private Long bookTypeId;

    @Column(name = "BOOK_TYPE_SUBJECT", nullable = false)
    @NotNull(message = "Book type subject is required")
    @Enumerated(value = EnumType.STRING)
    private BookTypeSubject bookTypeSubject;

    @Column(name = "BOOK_TYPE_AGE_GROUP", nullable = false)
    @NotNull(message = "Book type age group subject is required")
    @Enumerated(value = EnumType.STRING)
    private BookTypeAgeGroup bookTypeAgeGroup;

    @Column(name = "BOOK_TYPE_LANGUAGE", nullable = false)
    @NotNull(message = "Book type language is required")
    @Enumerated(value = EnumType.STRING)
    private BookTypeLanguage bookTypeLanguage;

    @Column(name = "PUBLICATION_STATUS", nullable = false)
    @NotNull(message = "Book type publication status is required")
    @Enumerated(value = EnumType.STRING)
    private BookTypePublicationStatus bookTypePublicationStatus;

    @Column(name = "ACADEMIC_LEVEL")
    @Enumerated(value = EnumType.STRING)
    private BookAcademicLevel bookAcademicLevel;

    @Column(name = "HISTORICAL_PERIOD_LEVEL")
    @Enumerated(value = EnumType.STRING)
    private HistoricalPeriodLevel historicalPeriodLevel;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    @NotNull(message = "Created date is required")
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    @NotNull(message = "Created time is required")
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", columnDefinition = "varchar(50)", nullable = false)
    @NotNull(message = "Created by is required")
    private String createdBy;

    public BookType() {
    }

    public BookType(Long bookTypeId, BookTypeSubject bookTypeSubject, BookTypeAgeGroup bookTypeAgeGroup, BookTypeLanguage bookTypeLanguage, BookTypePublicationStatus bookTypePublicationStatus, BookAcademicLevel bookAcademicLevel, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public BookType setBookTypeId(Long bookTypeId) {
        this.bookTypeId = bookTypeId;
        return this;
    }

    public BookTypeSubject getBookTypeSubject() {
        return bookTypeSubject;
    }

    public BookType setBookTypeSubject(BookTypeSubject bookTypeSubject) {
        this.bookTypeSubject = bookTypeSubject;
        return this;
    }

    public BookTypeAgeGroup getBookTypeAgeGroup() {
        return bookTypeAgeGroup;
    }

    public BookType setBookTypeAgeGroup(BookTypeAgeGroup bookTypeAgeGroup) {
        this.bookTypeAgeGroup = bookTypeAgeGroup;
        return this;
    }

    public BookTypeLanguage getBookTypeLanguage() {
        return bookTypeLanguage;
    }

    public BookType setBookTypeLanguage(BookTypeLanguage bookTypeLanguage) {
        this.bookTypeLanguage = bookTypeLanguage;
        return this;
    }

    public BookTypePublicationStatus getBookTypePublicationStatus() {
        return bookTypePublicationStatus;
    }

    public BookType setBookTypePublicationStatus(BookTypePublicationStatus bookTypePublicationStatus) {
        this.bookTypePublicationStatus = bookTypePublicationStatus;
        return this;
    }

    public BookAcademicLevel getBookAcademicLevel() {
        return bookAcademicLevel;
    }

    public BookType setBookAcademicLevel(BookAcademicLevel bookAcademicLevel) {
        this.bookAcademicLevel = bookAcademicLevel;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public BookType setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookType setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookType setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookType setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "bookTypeId=" + bookTypeId +
                ", bookTypeSubject=" + bookTypeSubject +
                ", bookTypeAgeGroup=" + bookTypeAgeGroup +
                ", bookTypeLanguage=" + bookTypeLanguage +
                ", bookTypePublicationStatus=" + bookTypePublicationStatus +
                ", bookAcademicLevel=" + bookAcademicLevel +
                ", historicalPeriodLevel=" + historicalPeriodLevel +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}