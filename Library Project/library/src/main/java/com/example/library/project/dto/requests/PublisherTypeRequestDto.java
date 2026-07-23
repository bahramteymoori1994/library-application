package com.example.library.project.dto.requests;

import java.time.LocalDate;
import java.time.LocalTime;

public class PublisherTypeRequestDto {

    private Long publisherTypeId;
    private String typeName;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PublisherTypeRequestDto() {
    }

    public PublisherTypeRequestDto(Long publisherTypeId, String typeName, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherTypeId = publisherTypeId;
        this.typeName = typeName;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherTypeId() {
        return publisherTypeId;
    }

    public PublisherTypeRequestDto setPublisherTypeId(Long publisherTypeId) {
        this.publisherTypeId = publisherTypeId;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public PublisherTypeRequestDto setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherTypeRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherTypeRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherTypeRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PublisherTypeRequestDto{" +
                "publisherTypeId=" + publisherTypeId +
                ", typeName='" + typeName + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}