package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "publisherTypeEntity")
@Table(name = "publisher_type")
public class PublisherType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLISHER_TYPE_ID")
    private Long publisherTypeId;

    @Column(name = "TYPE_NAME", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Publisher type name is required")
    private String typeName;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", columnDefinition = "varchar(50)", nullable = false)
    private String createdBy;

    public PublisherType() {
    }

    public PublisherType(Long publisherTypeId, String typeName, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherTypeId = publisherTypeId;
        this.typeName = typeName;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherTypeId() {
        return publisherTypeId;
    }

    public PublisherType setPublisherTypeId(Long publisherTypeId) {
        this.publisherTypeId = publisherTypeId;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public PublisherType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherType setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherType setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherType setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PublisherType{" +
                "publisherTypeId=" + publisherTypeId +
                ", typeName='" + typeName + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}