package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.LibraryType;

import java.time.LocalDate;
import java.time.LocalTime;

public class LibraryResponseDto {

    private Long libraryId;
    private String libraryName;
    private String libraryCode;
    private String city;
    private String phone;
    private String address;
    private LibraryType libraryType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public LibraryResponseDto() {
    }

    public LibraryResponseDto(Long libraryId, String libraryName, String libraryCode, String city, String phone, String address, LibraryType libraryType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public LibraryResponseDto setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public LibraryResponseDto setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public LibraryResponseDto setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public LibraryResponseDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public LibraryResponseDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LibraryResponseDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public LibraryType getLibraryType() {
        return libraryType;
    }

    public LibraryResponseDto setLibraryType(LibraryType libraryType) {
        this.libraryType = libraryType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LibraryResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public LibraryResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LibraryResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}