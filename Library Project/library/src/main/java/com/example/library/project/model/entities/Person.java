package com.example.library.project.model.entities;

import com.example.library.project.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "personEntity")
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "FIRST_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    @NotNull(message = "First Name is required")
    @Length(min = 3, max = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    @NotNull(message = "Last Name is required")
    @Length(min = 3, max = 50)
    private String lastName;

    @Column(name = "NATIONAL_CODE", nullable = false, unique = true, columnDefinition = "char(10)")
    @Pattern(regexp = "^[0-9]{10}$")
    @NotNull(message = "National Code is required")
    private String nationalCode;

    @Column(name = "FATHER_NAME", nullable = false, columnDefinition = "nvarchar(50)")
    @NotNull(message = "Father Name is required")
    @Length(min = 3, max = 50)
    private String fatherName;

    @Column(name = "BIRTH_DATE", columnDefinition = "date", nullable = false)
    @NotNull(message = "Birth Date is required")
    private LocalDate birthDate;

    @Column(name = "GENDER", nullable = false)
    @NotNull(message = "Gender is required")
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(name = "PHONE_NUMBER", nullable = false, unique = true, columnDefinition = "char(11)")
    @NotNull(message = "Phone Number is required")
    @Pattern(regexp = "^[0-9]{11}$")
    private String phoneNumber;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public Person() {
    }

    public Person(Long personId, String firstName, String lastName, String nationalCode, String fatherName, LocalDate birthDate, Gender gender, String phoneNumber, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public Person setPersonId(Long personId) {
        this.personId = personId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Person setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public Person setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
        return this;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Person setFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Person setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Person setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Person setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Person setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
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