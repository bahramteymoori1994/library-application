package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.Receipt;
import com.example.library.project.model.enums.ReceiptStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptLogResponseDto {

    private Long receiptLogId;
    private Receipt receipt;
    private ReceiptStatus receiptStatus;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public ReceiptLogResponseDto() {
    }

    public ReceiptLogResponseDto(Long receiptLogId, Receipt receipt, ReceiptStatus receiptStatus, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.receiptLogId = receiptLogId;
        this.receipt = receipt;
        this.receiptStatus = receiptStatus;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getReceiptLogId() {
        return receiptLogId;
    }

    public ReceiptLogResponseDto setReceiptLogId(Long receiptLogId) {
        this.receiptLogId = receiptLogId;
        return this;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public ReceiptLogResponseDto setReceipt(Receipt receipt) {
        this.receipt = receipt;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptLogResponseDto setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptLogResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptLogResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptLogResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}