package com.example.library.project.model.views;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Getter
@Entity
@Immutable
@Table(name = "book_view")
public class BookView {
    @Id
    @NotNull
    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Size(max = 50)
    @NotNull
    @Column(name = "book_title", nullable = false, length = 50)
    private String bookTitle;

    @Size(max = 10)
    @NotNull
    @Column(name = "book_isbn", nullable = false, length = 10)
    private String bookIsbn;

    @NotNull
    @Column(name = "book_count", nullable = false)
    private Integer bookCount;

    @NotNull
    @Column(name = "book_publish_date", nullable = false)
    private LocalDate bookPublishDate;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private LocalDate createdDate;

    @Size(max = 50)
    @NotNull
    @Column(name = "created_by", nullable = false, length = 50)
    private String createdBy;

    @NotNull
    @Lob
    @Column(name = "book_type_subject", nullable = false)
    private String bookTypeSubject;

    @NotNull
    @Lob
    @Column(name = "book_type_language", nullable = false)
    private String bookTypeLanguage;

    @Size(max = 50)
    @NotNull
    @Column(name = "author_first_name", nullable = false, length = 50)
    private String authorFirstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "author_last_name", nullable = false, length = 50)
    private String authorLastName;

    @NotNull
    @Column(name = "person_birth_date", nullable = false)
    private LocalDate personBirthDate;

    @Lob
    @Column(name = "author_expertise")
    private String authorExpertise;

    @Lob
    @Column(name = "author_writing_style")
    private String authorWritingStyle;

    @Size(max = 50)
    @Column(name = "library_name", length = 50)
    private String libraryName;

    @Size(max = 50)
    @Column(name = "library_city", length = 50)
    private String libraryCity;

    @Lob
    @Column(name = "library_ownership")
    private String libraryOwnership;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_name", nullable = false, length = 50)
    private String publisherName;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher_type_name", nullable = false, length = 50)
    private String publisherTypeName;

    public BookView() {
    }

    public BookView(Long bookId, String bookTitle, String bookIsbn, Integer bookCount, LocalDate bookPublishDate, LocalDate createdDate, String createdBy, String bookTypeSubject, String bookTypeLanguage, String authorFirstName, String authorLastName, LocalDate personBirthDate, String authorExpertise, String authorWritingStyle, String libraryName, String libraryCity, String libraryOwnership, String publisherName, String publisherTypeName) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
        this.bookCount = bookCount;
        this.bookPublishDate = bookPublishDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.bookTypeSubject = bookTypeSubject;
        this.bookTypeLanguage = bookTypeLanguage;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.personBirthDate = personBirthDate;
        this.authorExpertise = authorExpertise;
        this.authorWritingStyle = authorWritingStyle;
        this.libraryName = libraryName;
        this.libraryCity = libraryCity;
        this.libraryOwnership = libraryOwnership;
        this.publisherName = publisherName;
        this.publisherTypeName = publisherTypeName;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookView setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookView setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public BookView setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
        return this;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public BookView setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public LocalDate getBookPublishDate() {
        return bookPublishDate;
    }

    public BookView setBookPublishDate(LocalDate bookPublishDate) {
        this.bookPublishDate = bookPublishDate;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookView setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookView setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getBookTypeSubject() {
        return bookTypeSubject;
    }

    public BookView setBookTypeSubject(String bookTypeSubject) {
        this.bookTypeSubject = bookTypeSubject;
        return this;
    }

    public String getBookTypeLanguage() {
        return bookTypeLanguage;
    }

    public BookView setBookTypeLanguage(String bookTypeLanguage) {
        this.bookTypeLanguage = bookTypeLanguage;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public BookView setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public BookView setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public LocalDate getPersonBirthDate() {
        return personBirthDate;
    }

    public BookView setPersonBirthDate(LocalDate personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public String getAuthorExpertise() {
        return authorExpertise;
    }

    public BookView setAuthorExpertise(String authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public String getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public BookView setAuthorWritingStyle(String authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public BookView setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public BookView setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
        return this;
    }

    public String getLibraryOwnership() {
        return libraryOwnership;
    }

    public BookView setLibraryOwnership(String libraryOwnership) {
        this.libraryOwnership = libraryOwnership;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public BookView setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public String getPublisherTypeName() {
        return publisherTypeName;
    }

    public BookView setPublisherTypeName(String publisherTypeName) {
        this.publisherTypeName = publisherTypeName;
        return this;
    }

    @Override
    public String toString() {
        return "BookView{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookCount=" + bookCount +
                ", bookPublishDate=" + bookPublishDate +
                ", createdDate=" + createdDate +
                ", createdBy='" + createdBy + '\'' +
                ", bookTypeSubject='" + bookTypeSubject + '\'' +
                ", bookTypeLanguage='" + bookTypeLanguage + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", personBirthDate=" + personBirthDate +
                ", authorExpertise='" + authorExpertise + '\'' +
                ", authorWritingStyle='" + authorWritingStyle + '\'' +
                ", libraryName='" + libraryName + '\'' +
                ", libraryCity='" + libraryCity + '\'' +
                ", libraryOwnership='" + libraryOwnership + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publisherTypeName='" + publisherTypeName + '\'' +
                '}';
    }
}