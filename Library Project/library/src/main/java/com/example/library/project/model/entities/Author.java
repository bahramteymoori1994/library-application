package com.example.library.project.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHOR_ID")
    private Long authorId;

    @OneToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

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

    public Author(Long authorId, Person person, AuthorType authorType, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.authorId = authorId;
        this.person = person;
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

    public Person getPerson() {
        return person;
    }

    public Author setPerson(Person person) {
        this.person = person;
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
                ", person=" + person +
                ", authorType=" + authorType +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}