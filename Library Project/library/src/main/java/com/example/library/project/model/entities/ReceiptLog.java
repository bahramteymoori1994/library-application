package com.example.library.project.model.entities;

import com.example.library.project.model.enums.ReceiptStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "receipt_log")
public class ReceiptLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECEIPT_LOG_ID")
    private Long receiptLogId;

    @ManyToOne
    @JoinColumn(name = "RECEIPT_ID")
    private Receipt receipt;

    @Column(name = "RECEIPT_STATUS")
    @Enumerated(EnumType.STRING)
    private ReceiptStatus receiptStatus;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "date")
    @NotNull(message = "Created Date is required")
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", nullable = false, columnDefinition = "time")
    @NotNull(message = "Created Time is required")
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    @NotNull(message = "Created By is required")
    private String createdBy;

    public ReceiptLog() {
    }

    public ReceiptLog(Long receiptLogId, Receipt receipt, ReceiptStatus receiptStatus, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public ReceiptLog setReceiptLogId(Long receiptLogId) {
        this.receiptLogId = receiptLogId;
        return this;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public ReceiptLog setReceipt(Receipt receipt) {
        this.receipt = receipt;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public ReceiptLog setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public ReceiptLog setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public ReceiptLog setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public ReceiptLog setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "ReceiptLog{" +
                "receiptLogId=" + receiptLogId +
                ", receipt=" + receipt +
                ", receiptStatus=" + receiptStatus +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}