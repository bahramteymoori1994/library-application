package com.example.library.project.dto.responses;

import java.time.LocalDate;
import java.time.LocalTime;

public class PublisherTypeResponseDto {

    private Long publisherTypeId;
    private String typeName;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PublisherTypeResponseDto() {
    }

    public PublisherTypeResponseDto(Long publisherTypeId, String typeName, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherTypeId = publisherTypeId;
        this.typeName = typeName;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherTypeId() {
        return publisherTypeId;
    }

    public PublisherTypeResponseDto setPublisherTypeId(Long publisherTypeId) {
        this.publisherTypeId = publisherTypeId;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public PublisherTypeResponseDto setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherTypeResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherTypeResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherTypeResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PublisherTypeResponseDto{" +
                "publisherTypeId=" + publisherTypeId +
                ", typeName='" + typeName + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}