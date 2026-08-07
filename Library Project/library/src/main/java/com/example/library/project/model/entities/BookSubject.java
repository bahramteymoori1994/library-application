package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "book_subject")
public class BookSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_SUBJECT_ID")
    private Long bookSubjectId;

    @Column(name = "SUBJECT_TITLE", columnDefinition = "nvarchar(50)", nullable = false, unique = true)
    @NotNull(message = "Subject title is required")
    private String subjectTitle;

    @ManyToOne
    @JoinColumn(name = "BOOK_SUBJECT_PARENT_ID")
    private BookSubject bookSubject;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public BookSubject() {
    }

    public BookSubject(Long bookSubjectId, String subjectTitle, BookSubject bookSubject, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public BookSubject setBookSubjectId(Long bookSubjectId) {
        this.bookSubjectId = bookSubjectId;
        return this;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public BookSubject setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
        return this;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public BookSubject setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookSubject setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookSubject setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookSubject setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "BookSubject{" +
                "bookSubjectId=" + bookSubjectId +
                ", subjectTitle='" + subjectTitle + '\'' +
                ", bookSubject=" + bookSubject +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}