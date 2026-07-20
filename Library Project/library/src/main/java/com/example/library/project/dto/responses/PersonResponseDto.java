package com.example.library.project.dto.responses;

import com.example.library.project.model.enums.Gender;

import java.time.LocalDate;
import java.time.LocalTime;

public class PersonResponseDto {

    private Long personId;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String fatherName;
    private LocalDate birthDate;
    private Gender gender;
    private String phoneNumber;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public PersonResponseDto() {
    }

    public PersonResponseDto(Long personId, String firstName, String lastName, String nationalCode, String fatherName, LocalDate birthDate, Gender gender, String phoneNumber, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.fatherName = fatherName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getPersonId() {
        return personId;
    }

    public PersonResponseDto setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonResponseDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonResponseDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public PersonResponseDto setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public PersonResponseDto setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public PersonResponseDto setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public PersonResponseDto setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public PersonResponseDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public PersonResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public PersonResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public PersonResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "PersonResponseDto{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}