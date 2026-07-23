package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.PublisherType;
import java.time.LocalDate;
import java.time.LocalTime;

public class PublisherResponseDto {

    private Long publisherId;
    private String name;
    private String code;
    private String city;
    private String country;
    private String address;
    private String phone;
    private PublisherType publisherType;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PublisherResponseDto() {
    }

    public PublisherResponseDto(Long publisherId, String name, String code, String city, String country, String address, String phone, PublisherType publisherType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public PublisherResponseDto setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getName() {
        return name;
    }

    public PublisherResponseDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PublisherResponseDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCity() {
        return city;
    }

    public PublisherResponseDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public PublisherResponseDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PublisherResponseDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PublisherResponseDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public PublisherType getPublisherType() {
        return publisherType;
    }

    public PublisherResponseDto setPublisherType(PublisherType publisherType) {
        this.publisherType = publisherType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PublisherResponseDto{" +
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