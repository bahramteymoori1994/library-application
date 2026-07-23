package com.example.library.project.dto.requests;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class PublisherRequestDto {

    private Long publisherId;
    private String name;
    private String code;
    private String city;
    private String country;
    private String address;
    private String phone;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PublisherRequestDto() {
    }

    public PublisherRequestDto(Long publisherId, String name, String code, String city, String country, String address, String phone, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.publisherId = publisherId;
        this.name = name;
        this.code = code;
        this.city = city;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public PublisherRequestDto setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
        return this;
    }

    public String getName() {
        return name;
    }

    public PublisherRequestDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public PublisherRequestDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCity() {
        return city;
    }

    public PublisherRequestDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public PublisherRequestDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PublisherRequestDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public PublisherRequestDto setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PublisherRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PublisherRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PublisherRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PublisherRequestDto{" +
                "publisherId=" + publisherId +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}