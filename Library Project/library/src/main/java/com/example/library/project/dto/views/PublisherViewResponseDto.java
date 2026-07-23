package com.example.library.project.dto.views;

import java.time.LocalDate;
import java.time.LocalTime;

public class PublisherViewResponseDto {

    private Long publisherId;
    private String publisherName;
    private String publisherTypeName;
    private String publisherCode;
    private String publisherCountry;
    private String publisherCity;
    private String publisherAddress;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PublisherViewResponseDto() {
    }

    public PublisherViewResponseDto(Long publisherId, String publisherName, String publisherTypeName, String publisherCode, String publisherCountry, String publisherCity, String publisherAddress, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public PublisherViewResponseDto setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public PublisherViewResponseDto setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public String getPublisherTypeName() {
        return publisherTypeName;
    }

    public PublisherViewResponseDto setPublisherTypeName(String publisherTypeName) {
        this.publisherTypeName = publisherTypeName;
        return this;
    }

    public String getPublisherCode() {
        return publisherCode;
    }

    public PublisherViewResponseDto setPublisherCode(String publisherCode) {
        this.publisherCode = publisherCode;
        return this;
    }

    public String getPublisherCountry() {
        return publisherCountry;
    }

    public PublisherViewResponseDto setPublisherCountry(String publisherCountry) {
        this.publisherCountry = publisherCountry;
        return this;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public PublisherViewResponseDto setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
        return this;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public PublisherViewResponseDto setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherViewResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherViewResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherViewResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}