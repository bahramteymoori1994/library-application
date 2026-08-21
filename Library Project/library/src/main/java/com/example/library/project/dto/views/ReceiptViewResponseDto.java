package com.example.library.project.dto.views;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptViewResponseDto {

    private Long receiptId;
    private Long userId;
    private String username;
    private LocalDate receiptDate;
    private LocalTime receiptTime;
    private Long bookId;
    private String bookTitle;
    private String isbn;
    private Short bookPublishYear;
    private Long publisherId;
    private String publisherName;
    private Long bookSubjectId;
    private String bookSubjectTitle;
    private Integer receiptBookCount;
    private String receiptStatus;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public ReceiptViewResponseDto() {
    }

    public ReceiptViewResponseDto(Long receiptId, Long userId, String username, LocalDate receiptDate, LocalTime receiptTime, Long bookId, String bookTitle, String isbn, Short bookPublishYear, Long publisherId, String publisherName, Long bookSubjectId, String bookSubjectTitle, Integer receiptBookCount, String receiptStatus, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public ReceiptViewResponseDto setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public ReceiptViewResponseDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ReceiptViewResponseDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public ReceiptViewResponseDto setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public ReceiptViewResponseDto setReceiptTime(LocalTime receiptTime) {
        this.receiptTime = receiptTime;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public ReceiptViewResponseDto setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public ReceiptViewResponseDto setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public ReceiptViewResponseDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Short getBookPublishYear() {
        return bookPublishYear;
    }

    public ReceiptViewResponseDto setBookPublishYear(Short bookPublishYear) {
        this.bookPublishYear = bookPublishYear;
        return this;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public ReceiptViewResponseDto setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public ReceiptViewResponseDto setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public Long getBookSubjectId() {
        return bookSubjectId;
    }

    public ReceiptViewResponseDto setBookSubjectId(Long bookSubjectId) {
        this.bookSubjectId = bookSubjectId;
        return this;
    }

    public String getBookSubjectTitle() {
        return bookSubjectTitle;
    }

    public ReceiptViewResponseDto setBookSubjectTitle(String bookSubjectTitle) {
        this.bookSubjectTitle = bookSubjectTitle;
        return this;
    }

    public Integer getReceiptBookCount() {
        return receiptBookCount;
    }

    public ReceiptViewResponseDto setReceiptBookCount(Integer receiptBookCount) {
        this.receiptBookCount = receiptBookCount;
        return this;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptViewResponseDto setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptViewResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptViewResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptViewResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}