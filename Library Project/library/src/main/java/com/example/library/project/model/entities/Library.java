package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LIBRARY_ID")
    private Long libraryId;

    @Column(name = "LIBRARY_NAME", columnDefinition = "nvarchar(50)", nullable = false, unique = true)
    @NotNull(message = "Library name is required")
    private String libraryName;

    @Column(name = "LIBRARY_CODE", columnDefinition = "varchar(10)", nullable = false, unique = true)
    @NotNull(message = "Library code is requried")
    private String libraryCode;

    @Column(name = "LIBRARY_CITY", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Library city is required")
    private String city;

    @Column(name = "LIBRARY_PHONE", columnDefinition = "varchar(11)", nullable = false, unique = true)
    @NotNull(message = "Library phone is required")
    private String phone;

    @Column(name = "LIBRARY_ADDRESS", columnDefinition = "nvarchar(2000)")
    private String address;

    @OneToOne
    @JoinColumn(name = "LIBRARY_TYPE_ID")
    private LibraryType libraryType;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public Library() {
    }

    public Library(Long libraryId, String libraryName, String libraryCode, String city, String phone, String address, LibraryType libraryType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.libraryCode = libraryCode;
        this.city = city;
        this.phone = phone;
        this.address = address;
        this.libraryType = libraryType;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public Library setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public Library setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public Library setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Library setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Library setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Library setAddress(String address) {
        this.address = address;
        return this;
    }

    public LibraryType getLibraryType() {
        return libraryType;
    }

    public Library setLibraryType(LibraryType libraryType) {
        this.libraryType = libraryType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Library setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Library setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Library setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libraryId=" + libraryId +
                ", libraryName='" + libraryName + '\'' +
                ", libraryCode='" + libraryCode + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", libraryType=" + libraryType +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}