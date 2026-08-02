package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.Receipt;
import com.example.library.project.model.enums.ReceiptStatus;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReceiptLogRequestDto {

    private Long receiptLogId;
    private Receipt receipt;
    private ReceiptStatus receiptStatus;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public ReceiptLogRequestDto() {
    }

    public ReceiptLogRequestDto(Long receiptLogId, Receipt receipt, ReceiptStatus receiptStatus, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public ReceiptLogRequestDto setReceiptLogId(Long receiptLogId) {
        this.receiptLogId = receiptLogId;
        return this;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public ReceiptLogRequestDto setReceipt(Receipt receipt) {
        this.receipt = receipt;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptLogRequestDto setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptLogRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptLogRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptLogRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}