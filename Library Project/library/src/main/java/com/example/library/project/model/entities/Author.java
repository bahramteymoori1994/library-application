package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "author")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @Column(name = "FIRST_NAME", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Author first name is required")
    private String firstName;

    @Column(name = "LAST_NAME", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Author last name is required")
    private String lastName;

    @OneToOne
    @JoinColumn(name = "AUTHOR_TYPE_ID")
    private AuthorType authorType;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public Author() {
    }

    public Author(Long authorId, String firstName, String lastName, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorType = authorType;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Author setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Author setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Author setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public AuthorType getAuthorType() {
        return authorType;
    }

    public Author setAuthorType(AuthorType authorType) {
        this.authorType = authorType;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Author setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Author setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Author setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", authorType=" + authorType +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}