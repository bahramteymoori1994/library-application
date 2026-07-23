package com.example.library.project.model.entities;

import com.example.library.project.model.enums.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "libraryTypeEntity")
@Table(name = "library_type")
public class LibraryType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LIBRARY_TYPE_ID")
    private Long libraryTypeId;

    @Column(name = "LIBRARY_TYPE_ACCESSIBILITY")
    @Enumerated(value = EnumType.STRING)
    private LibraryTypeAccessibility libraryTypeAccessibility;

    @Column(name = "LIBRARY_TYPE_OWNERSHIP")
    @Enumerated(value = EnumType.STRING)
    private LibraryTypeOwnership libraryTypeOwnership;

    @Column(name = "LIBRARY_TYPE_SCOPE")
    @Enumerated(value = EnumType.STRING)
    private LibraryTypeScope libraryTypeScope;

    @Column(name = "LIBRARY_TYPE_SERVICE")
    @Enumerated(value = EnumType.STRING)
    private LibraryTypeService libraryTypeService;

    @Column(name = "LIBRARY_TYPE_SIZE")
    @Enumerated(value = EnumType.STRING)
    private LibraryTypeSize libraryTypeSize;

    @Column(name = "HISTORICAL_PERIOD_LEVEL")
    @Enumerated(value = EnumType.STRING)
    private HistoricalPeriodLevel historicalPeriodLevel;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public LibraryType() {
    }

    public LibraryType(Long libraryTypeId, LibraryTypeAccessibility libraryTypeAccessibility, LibraryTypeOwnership libraryTypeOwnership, LibraryTypeScope libraryTypeScope, LibraryTypeService libraryTypeService, LibraryTypeSize libraryTypeSize, HistoricalPeriodLevel historicalPeriodLevel, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public LibraryType setLibraryTypeId(Long libraryTypeId) {
        this.libraryTypeId = libraryTypeId;
        return this;
    }

    public LibraryTypeAccessibility getLibraryTypeAccessibility() {
        return libraryTypeAccessibility;
    }

    public LibraryType setLibraryTypeAccessibility(LibraryTypeAccessibility libraryTypeAccessibility) {
        this.libraryTypeAccessibility = libraryTypeAccessibility;
        return this;
    }

    public LibraryTypeOwnership getLibraryTypeOwnership() {
        return libraryTypeOwnership;
    }

    public LibraryType setLibraryTypeOwnership(LibraryTypeOwnership libraryTypeOwnership) {
        this.libraryTypeOwnership = libraryTypeOwnership;
        return this;
    }

    public LibraryTypeScope getLibraryTypeScope() {
        return libraryTypeScope;
    }

    public LibraryType setLibraryTypeScope(LibraryTypeScope libraryTypeScope) {
        this.libraryTypeScope = libraryTypeScope;
        return this;
    }

    public LibraryTypeService getLibraryTypeService() {
        return libraryTypeService;
    }

    public LibraryType setLibraryTypeService(LibraryTypeService libraryTypeService) {
        this.libraryTypeService = libraryTypeService;
        return this;
    }

    public LibraryTypeSize getLibraryTypeSize() {
        return libraryTypeSize;
    }

    public LibraryType setLibraryTypeSize(LibraryTypeSize libraryTypeSize) {
        this.libraryTypeSize = libraryTypeSize;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public LibraryType setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LibraryType setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public LibraryType setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LibraryType setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "LibraryType{" +
                "libraryTypeId=" + libraryTypeId +
                ", libraryTypeAccessibility=" + libraryTypeAccessibility +
                ", libraryTypeOwnership=" + libraryTypeOwnership +
                ", libraryTypeScope=" + libraryTypeScope +
                ", libraryTypeService=" + libraryTypeService +
                ", libraryTypeSize=" + libraryTypeSize +
                ", historicalPeriodLevel=" + historicalPeriodLevel +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}