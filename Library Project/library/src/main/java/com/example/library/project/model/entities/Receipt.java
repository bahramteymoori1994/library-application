package com.example.library.project.model.entities;

import com.example.library.project.model.enums.ReceiptStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECEIPT_ID")
    private Long receiptId;

    @Column(name = "RECEIPT_DATE", columnDefinition = "date", nullable = false)
    @NotNull(message = "Receipt date is required")
    private LocalDate receiptDate;

    @Column(name = "RECEIPT_TIME", columnDefinition = "time", nullable = false)
    @NotNull(message = "Receipt time is required")
    private LocalTime receiptTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "book_receipt", joinColumns = @JoinColumn(name = "RECEIPT_ID", referencedColumnName = "RECEIPT_ID"),
    foreignKey = @ForeignKey(name = "FK_RECEIPT_ID"), inverseJoinColumns = @JoinColumn(name = "BOOK_ID",
    referencedColumnName = "BOOK_ID", foreignKey = @ForeignKey(name = "FK_BOOK_ID")))
    private List<Book> books = new ArrayList<>();

    @Column(name = "RECEIPT_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReceiptStatus receiptStatus;

    @Column(name = "RECEIPT_BOOK_COUNT")
    private Integer ReceiptBookCount;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "date")
    @NotNull(message = "Created Date is required")
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", nullable = false, columnDefinition = "time")
    @NotNull(message = "Created Time is required")
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    @NotNull(message = "Created By is required")
    private String createdBy;

    @Column(name = "MODIFIED_DATE", columnDefinition = "date")
    private LocalDate modifiedDate;

    @Column(name = "MODIFIED_TIME", columnDefinition = "time")
    private LocalTime modifiedTime;

    @Column(name = "MODIFIED_BY", columnDefinition = "varchar(50)")
    private String modifiedBy;

    public Receipt() {
    }

    public Receipt(Long receiptId, LocalDate receiptDate, LocalTime receiptTime, User user, List<Book> books, ReceiptStatus receiptStatus, Integer receiptBookCount, LocalDate createdDate, LocalTime createdTime, String createdBy, LocalDate modifiedDate, LocalTime modifiedTime, String modifiedBy) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.receiptTime = receiptTime;
        this.user = user;
        this.books = books;
        this.receiptStatus = receiptStatus;
        ReceiptBookCount = receiptBookCount;
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

    public Receipt setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }

    public Receipt setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public Receipt setReceiptTime(LocalTime receiptTime) {
        this.receiptTime = receiptTime;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Receipt setUser(User user) {
        this.user = user;
        return this;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Receipt setBooks(List<Book> books) {
        this.books = books;
        return this;
    }

    public ReceiptStatus getReceiptStatus() {
        return receiptStatus;
    }

    public Receipt setReceiptStatus(ReceiptStatus receiptStatus) {
        this.receiptStatus = receiptStatus;
        return this;
    }

    public Integer getReceiptBookCount() {
        return ReceiptBookCount;
    }

    public Receipt setReceiptBookCount(Integer receiptBookCount) {
        ReceiptBookCount = receiptBookCount;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Receipt setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Receipt setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Receipt setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }

    public Receipt setModifiedDate(LocalDate modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public LocalTime getModifiedTime() {
        return modifiedTime;
    }

    public Receipt setModifiedTime(LocalTime modifiedTime) {
        this.modifiedTime = modifiedTime;
        return this;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Receipt setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", receiptDate=" + receiptDate +
                ", receiptTime=" + receiptTime +
                ", user=" + user +
                ", books=" + books +
                ", receiptStatus=" + receiptStatus +
                ", ReceiptBookCount=" + ReceiptBookCount +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", modifiedTime=" + modifiedTime +
                ", modifiedBy='" + modifiedBy + '\'' +
                '}';
    }
}