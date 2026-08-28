package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.Book;
import com.example.library.project.model.entities.User;
import com.example.library.project.model.enums.ReceiptStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReceiptRequestDto {

    private Long receiptId;
    private LocalDate receiptDate;
    private LocalTime receiptTime;
    private User user;
    private List<Book> books = new ArrayList<>();
    private ReceiptStatus receiptStatus;
    private Integer receiptBookCount;
    private String description;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;
    private LocalDate modifiedDate;
    private LocalTime modifiedTime;
    private String modifiedBy;

    public ReceiptRequestDto() {
    }

    public ReceiptRequestDto(Long receiptId, LocalDate receiptDate, LocalTime receiptTime, User user, List<Book> books, ReceiptStatus receiptStatus, Integer receiptBookCount, String description, LocalDate createdDate, LocalTime createdTime, String createdBy, LocalDate modifiedDate, LocalTime modifiedTime, String modifiedBy) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.receiptTime = receiptTime;
        this.user = user;
        this.books = books;
        this.receiptStatus = receiptStatus;
        this.description = description;
        this.receiptBookCount = receiptBookCount;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedTime = modifiedTime;
        this.modifiedBy = modifiedBy;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public ReceiptRequestDto setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public ReceiptRequestDto setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public ReceiptRequestDto setReceiptTime(LocalTime receiptTime) {
        this.receiptTime = receiptTime;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ReceiptRequestDto setUser(User user) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public ReceiptRequestDto setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptRequestDto setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public Integer getReceiptBookCount() {
        return receiptBookCount;
    }

    public ReceiptRequestDto setReceiptBookCount(Integer receiptBookCount) {
        this.receiptBookCount = receiptBookCount;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public ReceiptRequestDto setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public ReceiptRequestDto setModifiedTime(LocalTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public ReceiptRequestDto setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }
}