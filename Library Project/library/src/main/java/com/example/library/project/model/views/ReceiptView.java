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
@Table(name = "receipt_view")
public class ReceiptView {
    @Id
    @NotNull
    @Column(name = "receipt_id", nullable = false)
    private Long receiptId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @NotNull
    @Column(name = "receipt_date", nullable = false)
    private LocalDate receiptDate;

    @NotNull
    @Column(name = "receipt_time", nullable = false)
    private LocalTime receiptTime;

    @NotNull
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Size(max = 50)
    @NotNull
    @Column(name = "book_title", nullable = false, length = 50)
    private String bookTitle;

    @Size(max = 10)
    @NotNull
    @Column(name = "isbn", nullable = false, length = 10)
    private String isbn;

    @NotNull
    @Column(name = "book_publish_year", nullable = false)
    private Short bookPublishYear;

    @NotNull
    @Column(name = "publisher_id", nullable = false)
    private Long publisherId;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_name", nullable = false, length = 50)
    private String publisherName;

    @NotNull
    @Column(name = "book_subject_id", nullable = false)
    private Long bookSubjectId;

    @Size(max = 50)
    @NotNull
    @Column(name = "book_subject_title", nullable = false, length = 50)
    private String bookSubjectTitle;

    @Column(name = "receipt_book_count")
    private Integer receiptBookCount;

    @NotNull
    @Lob
    @Column(name = "receipt_status", nullable = false)
    private String receiptStatus;

    @Size(max = 2000)
    @Column(name = "description", nullable = true,  length = 2000)
    private String description;

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

    public ReceiptView() {
    }

    public ReceiptView(Long receiptId, Long userId, String username, LocalDate receiptDate, LocalTime receiptTime, Long bookId, String bookTitle, String isbn, Short bookPublishYear, Long publisherId, String publisherName, Long bookSubjectId, String bookSubjectTitle, Integer receiptBookCount, String receiptStatus, String description, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.receiptId = receiptId;
        this.userId = userId;
        this.username = username;
        this.receiptDate = receiptDate;
        this.receiptTime = receiptTime;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.bookPublishYear = bookPublishYear;
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.bookSubjectId = bookSubjectId;
        this.bookSubjectTitle = bookSubjectTitle;
        this.receiptBookCount = receiptBookCount;
        this.receiptStatus = receiptStatus;
        this.description = description;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public ReceiptView setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ReceiptView setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReceiptView setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public ReceiptView setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public ReceiptView setReceiptTime(LocalTime receiptTime) {
        this.receiptTime = receiptTime;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public ReceiptView setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public ReceiptView setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public ReceiptView setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Short getBookPublishYear() {
        return bookPublishYear;
    }

    public ReceiptView setBookPublishYear(Short bookPublishYear) {
        this.bookPublishYear = bookPublishYear;
        return this;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public ReceiptView setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public ReceiptView setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public Long getBookSubjectId() {
        return bookSubjectId;
    }

    public ReceiptView setBookSubjectId(Long bookSubjectId) {
        this.bookSubjectId = bookSubjectId;
        return this;
    }

    public String getBookSubjectTitle() {
        return bookSubjectTitle;
    }

    public ReceiptView setBookSubjectTitle(String bookSubjectTitle) {
        this.bookSubjectTitle = bookSubjectTitle;
        return this;
    }

    public Integer getReceiptBookCount() {
        return receiptBookCount;
    }

    public ReceiptView setReceiptBookCount(Integer receiptBookCount) {
        this.receiptBookCount = receiptBookCount;
        return this;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptView setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptView setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptView setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptView setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptView setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "ReceiptView{" +
                "receiptId=" + receiptId +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", receiptDate=" + receiptDate +
                ", receiptTime=" + receiptTime +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", isbn='" + isbn + '\'' +
                ", bookPublishYear=" + bookPublishYear +
                ", publisherId=" + publisherId +
                ", publisherName='" + publisherName + '\'' +
                ", bookSubjectId=" + bookSubjectId +
                ", bookSubjectTitle='" + bookSubjectTitle + '\'' +
                ", receiptBookCount=" + receiptBookCount +
                ", receiptStatus='" + receiptStatus + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}