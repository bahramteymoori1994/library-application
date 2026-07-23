package com.example.library.project.dto.requests;

import com.example.library.project.model.enums.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class LibraryTypeRequestDto {

    private Long libraryTypeId;
    private LibraryTypeAccessibility libraryTypeAccessibility;
    private LibraryTypeOwnership libraryTypeOwnership;
    private LibraryTypeScope libraryTypeScope;
    private LibraryTypeService libraryTypeService;
    private LibraryTypeSize libraryTypeSize;
    private HistoricalPeriodLevel historicalPeriodLevel;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public LibraryTypeRequestDto() {
    }

    public LibraryTypeRequestDto(Long libraryTypeId, LibraryTypeAccessibility libraryTypeAccessibility, LibraryTypeOwnership libraryTypeOwnership, LibraryTypeScope libraryTypeScope, LibraryTypeService libraryTypeService, LibraryTypeSize libraryTypeSize, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.libraryTypeId = libraryTypeId;
        this.libraryTypeAccessibility = libraryTypeAccessibility;
        this.libraryTypeOwnership = libraryTypeOwnership;
        this.libraryTypeScope = libraryTypeScope;
        this.libraryTypeService = libraryTypeService;
        this.libraryTypeSize = libraryTypeSize;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getLibraryTypeId() {
        return libraryTypeId;
    }

    public LibraryTypeRequestDto setLibraryTypeId(Long libraryTypeId) {
        this.libraryTypeId = libraryTypeId;
        return this;
    }

    public LibraryTypeAccessibility getLibraryTypeAccessibility() {
        return libraryTypeAccessibility;
    }

    public LibraryTypeRequestDto setLibraryTypeAccessibility(LibraryTypeAccessibility libraryTypeAccessibility) {
        this.libraryTypeAccessibility = libraryTypeAccessibility;
        return this;
    }

    public LibraryTypeOwnership getLibraryTypeOwnership() {
        return libraryTypeOwnership;
    }

    public LibraryTypeRequestDto setLibraryTypeOwnership(LibraryTypeOwnership libraryTypeOwnership) {
        this.libraryTypeOwnership = libraryTypeOwnership;
        return this;
    }

    public LibraryTypeScope getLibraryTypeScope() {
        return libraryTypeScope;
    }

    public LibraryTypeRequestDto setLibraryTypeScope(LibraryTypeScope libraryTypeScope) {
        this.libraryTypeScope = libraryTypeScope;
        return this;
    }

    public LibraryTypeService getLibraryTypeService() {
        return libraryTypeService;
    }

    public LibraryTypeRequestDto setLibraryTypeService(LibraryTypeService libraryTypeService) {
        this.libraryTypeService = libraryTypeService;
        return this;
    }

    public LibraryTypeSize getLibraryTypeSize() {
        return libraryTypeSize;
    }

    public LibraryTypeRequestDto setLibraryTypeSize(LibraryTypeSize libraryTypeSize) {
        this.libraryTypeSize = libraryTypeSize;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public LibraryTypeRequestDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LibraryTypeRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public LibraryTypeRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LibraryTypeRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}