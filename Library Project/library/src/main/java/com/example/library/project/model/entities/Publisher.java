package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "publisherEntity")
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBLISHER_ID")
    private Long publisherId;

    @Column(name = "NAME", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Publisher name is required")
    private String name;

    @Column(name = "CODE", columnDefinition = "varchar(5)", nullable = false, unique = true)
    @NotNull(message = "Publisher code is required")
    private String code;

    @Column(name = "CITY", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Publisher city is required")
    private String city;

    @Column(name = "COUNTRY", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Publisher country is required")
    private String country;

    @Column(name = "ADDRESS", columnDefinition = "nvarchar(2000)")
    private String address;

    @Column(name = "PHONE", columnDefinition = "varchar(15)", unique = true)
    private String phone;

    @OneToOne
    @JoinColumn(name = "PUBLISHER_TYPE_ID")
    private PublisherType publisherType;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", columnDefinition = "varchar(50)", nullable = false)
    private String createdBy;

    public Publisher() {
    }

    public Publisher(Long publisherId, String name, String code, String city, String country, String address, String phone, PublisherType publisherType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherId = publisherId;
        this.name = name;
        this.code = code;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.publisherType = publisherType;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public Publisher setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Publisher setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Publisher setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Publisher setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Publisher setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Publisher setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Publisher setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PublisherType getPublisherType() {
        return publisherType;
    }

    public Publisher setPublisherType(PublisherType publisherType) {
        this.publisherType = publisherType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Publisher setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Publisher setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Publisher setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", publisherType=" + publisherType +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}