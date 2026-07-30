package com.example.library.project.dto.views;

import java.time.LocalDate;

public class LibraryViewResponseDto {

    private Long libraryId;
    private String libraryName;
    private String libraryTypeOwnership;
    private String libraryTypeService;
    private String libararyCode;
    private String libraryCity;
    private String libraryPhone;
    private String libraryAddress;
    private LocalDate createdDate;
    private String createdBy;

    public LibraryViewResponseDto() {
    }

    public LibraryViewResponseDto(Long libraryId, String libraryName, String libraryTypeOwnership, String libraryTypeService, String libararyCode, String libraryCity, String libraryPhone, String libraryAddress, LocalDate createdDate, String createdBy) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.libraryTypeOwnership = libraryTypeOwnership;
        this.libraryTypeService = libraryTypeService;
        this.libararyCode = libararyCode;
        this.libraryCity = libraryCity;
        this.libraryPhone = libraryPhone;
        this.libraryAddress = libraryAddress;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public LibraryViewResponseDto setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public LibraryViewResponseDto setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryTypeOwnership() {
        return libraryTypeOwnership;
    }

    public LibraryViewResponseDto setLibraryTypeOwnership(String libraryTypeOwnership) {
        this.libraryTypeOwnership = libraryTypeOwnership;
        return this;
    }

    public String getLibraryTypeService() {
        return libraryTypeService;
    }

    public LibraryViewResponseDto setLibraryTypeService(String libraryTypeService) {
        this.libraryTypeService = libraryTypeService;
        return this;
    }

    public String getLibararyCode() {
        return libararyCode;
    }

    public LibraryViewResponseDto setLibararyCode(String libararyCode) {
        this.libararyCode = libararyCode;
        return this;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public LibraryViewResponseDto setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
        return this;
    }

    public String getLibraryPhone() {
        return libraryPhone;
    }

    public LibraryViewResponseDto setLibraryPhone(String libraryPhone) {
        this.libraryPhone = libraryPhone;
        return this;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public LibraryViewResponseDto setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LibraryViewResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LibraryViewResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}