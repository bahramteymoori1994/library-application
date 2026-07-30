package com.example.library.project.model.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Getter
@Entity
@Immutable
@Table(name = "library_view")
public class LibraryView {
    @Id
    @NotNull
    @Column(name = "library_id", nullable = false)
    private Long libraryId;

    @Size(max = 50)
    @NotNull
    @Column(name = "library_name", nullable = false, length = 50)
    private String libraryName;

    @Lob
    @Column(name = "library_type_ownership")
    private String libraryTypeOwnership;

    @Lob
    @Column(name = "library_type_service")
    private String libraryTypeService;

    @Size(max = 10)
    @NotNull
    @Column(name = "libarary_code", nullable = false, length = 10)
    private String libararyCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "library_city", nullable = false, length = 50)
    private String libraryCity;

    @Size(max = 11)
    @NotNull
    @Column(name = "library_phone", nullable = false, length = 11)
    private String libraryPhone;

    @Size(max = 2000)
    @Column(name = "library_address", length = 2000)
    private String libraryAddress;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    public LibraryView() {
    }

    public LibraryView(Long libraryId, String libraryName, String libraryTypeOwnership, String libraryTypeService, String libararyCode, String libraryCity, String libraryPhone, String libraryAddress, LocalDate createdDate, String createdBy) {
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

    public LibraryView setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public LibraryView setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryTypeOwnership() {
        return libraryTypeOwnership;
    }

    public LibraryView setLibraryTypeOwnership(String libraryTypeOwnership) {
        this.libraryTypeOwnership = libraryTypeOwnership;
        return this;
    }

    public String getLibraryTypeService() {
        return libraryTypeService;
    }

    public LibraryView setLibraryTypeService(String libraryTypeService) {
        this.libraryTypeService = libraryTypeService;
        return this;
    }

    public String getLibararyCode() {
        return libararyCode;
    }

    public LibraryView setLibararyCode(String libararyCode) {
        this.libararyCode = libararyCode;
        return this;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public LibraryView setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
        return this;
    }

    public String getLibraryPhone() {
        return libraryPhone;
    }

    public LibraryView setLibraryPhone(String libraryPhone) {
        this.libraryPhone = libraryPhone;
        return this;
    }

    public String getLibraryAddress() {
        return libraryAddress;
    }

    public LibraryView setLibraryAddress(String libraryAddress) {
        this.libraryAddress = libraryAddress;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LibraryView setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LibraryView setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "LibraryView{" +
                "libraryId=" + libraryId +
                ", libraryName='" + libraryName + '\'' +
                ", libraryTypeOwnership='" + libraryTypeOwnership + '\'' +
                ", libraryTypeService='" + libraryTypeService + '\'' +
                ", libararyCode='" + libararyCode + '\'' +
                ", libraryCity='" + libraryCity + '\'' +
                ", libraryPhone='" + libraryPhone + '\'' +
                ", libraryAddress='" + libraryAddress + '\'' +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}