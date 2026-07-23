package com.example.library.project.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Immutable
@Table(name = "publisher_view")
public class PublisherView {
    @Id
    @NotNull
    @Column(name = "publisher_id", nullable = false)
    private Long publisherId;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_name", nullable = false, length = 50)
    private String publisherName;

    @Size(max = 50)
    @Column(name = "publisher_type_name", length = 50)
    private String publisherTypeName;

    @Size(max = 5)
    @NotNull
    @Column(name = "publisher_code", nullable = false, length = 5)
    private String publisherCode;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_country", nullable = false, length = 50)
    private String publisherCountry;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_city", nullable = false, length = 50)
    private String publisherCity;

    @Size(max = 2000)
    @Column(name = "publisher_address", length = 2000)
    private String publisherAddress;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "created_time", nullable = false)
    private LocalTime createdTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    public PublisherView() {
    }

    public PublisherView(Long publisherId, String publisherName, String publisherTypeName, String publisherCode, String publisherCountry, String publisherCity, String publisherAddress, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.publisherTypeName = publisherTypeName;
        this.publisherCode = publisherCode;
        this.publisherCountry = publisherCountry;
        this.publisherCity = publisherCity;
        this.publisherAddress = publisherAddress;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public PublisherView setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public PublisherView setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public String getPublisherTypeName() {
        return publisherTypeName;
    }

    public PublisherView setPublisherTypeName(String publisherTypeName) {
        this.publisherTypeName = publisherTypeName;
        return this;
    }

    public String getPublisherCode() {
        return publisherCode;
    }

    public PublisherView setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode;
        return this;
    }

    public String getPublisherCountry() {
        return publisherCountry;
    }

    public PublisherView setPublisherCountry(String publisherCountry) {
        this.publisherCountry = publisherCountry;
        return this;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public PublisherView setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
        return this;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public PublisherView setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherView setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherView setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherView setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}