package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.Book;
import com.example.library.project.model.entities.User;
import com.example.library.project.model.enums.ReceiptStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ReceiptResponseDto {

    private Long receiptId;
    private LocalDate receiptDate;
    private LocalTime receiptTime;
    private User user;
    private List<Book> books = new ArrayList<>();
    private ReceiptStatus receiptStatus;
    private Integer ReceiptBookCount;
    private String description;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;
    private LocalDate modifiedDate;
    private LocalTime modifiedTime;
    private String modifiedBy;


    public ReceiptResponseDto() {
    }

    public ReceiptResponseDto(Long receiptId, LocalDate receiptDate, LocalTime receiptTime, User user, List<Book> books, ReceiptStatus receiptStatus, Integer receiptBookCount, String description, LocalDate createdDate, LocalTime createdTime, String createdBy, LocalDate modifiedDate, LocalTime modifiedTime, String modifiedBy) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.receiptTime = receiptTime;
        this.user = user;
        this.books = books;
        this.receiptStatus = receiptStatus;
        ReceiptBookCount = receiptBookCount;
        this.description = description;
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

    public ReceiptResponseDto setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public ReceiptResponseDto setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public ReceiptResponseDto setReceiptTime(LocalTime receiptTime) {
        this.receiptTime = receiptTime;
        return this;
    }

    public User getUser() {
        return user;
    }

    public ReceiptResponseDto setUser(User user) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReceiptResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public ReceiptResponseDto setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptResponseDto setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public Integer getReceiptBookCount() {
        return ReceiptBookCount;
    }

    public ReceiptResponseDto setReceiptBookCount(Integer receiptBookCount) {
        ReceiptBookCount = receiptBookCount;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public ReceiptResponseDto setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public ReceiptResponseDto setModifiedTime(LocalTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public ReceiptResponseDto setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }
}