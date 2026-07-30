package com.example.library.project.dto.views;

import java.time.LocalDate;

public class BookViewResponseDto {

    private Long bookId;
    private String bookTitle;
    private String bookIsbn;
    private Integer bookCount;
    private LocalDate bookPublishDate;
    private LocalDate createdDate;
    private String createdBy;
    private String bookTypeSubject;
    private String bookTypeLanguage;
    private String authorFirstName;
    private String authorLastName;
    private LocalDate personBirthDate;
    private String authorExpertise;
    private String authorWritingStyle;
    private String libraryName;
    private String libraryCity;
    private String libraryOwnership;
    private String publisherName;
    private String publisherTypeName;

    public BookViewResponseDto() {
    }

    public BookViewResponseDto(Long bookId, String bookTitle, String bookIsbn, Integer bookCount, LocalDate bookPublishDate, LocalDate createdDate, String createdBy, String bookTypeSubject, String bookTypeLanguage, String authorFirstName, String authorLastName, LocalDate personBirthDate, String authorExpertise, String authorWritingStyle, String libraryName, String libraryCity, String libraryOwnership, String publisherName, String publisherTypeName) {
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

    public BookViewResponseDto setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookViewResponseDto setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public BookViewResponseDto setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
        return this;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public BookViewResponseDto setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public LocalDate getBookPublishDate() {
        return bookPublishDate;
    }

    public BookViewResponseDto setBookPublishDate(LocalDate bookPublishDate) {
        this.bookPublishDate = bookPublishDate;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookViewResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookViewResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getBookTypeSubject() {
        return bookTypeSubject;
    }

    public BookViewResponseDto setBookTypeSubject(String bookTypeSubject) {
        this.bookTypeSubject = bookTypeSubject;
        return this;
    }

    public String getBookTypeLanguage() {
        return bookTypeLanguage;
    }

    public BookViewResponseDto setBookTypeLanguage(String bookTypeLanguage) {
        this.bookTypeLanguage = bookTypeLanguage;
        return this;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public BookViewResponseDto setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public BookViewResponseDto setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }

    public LocalDate getPersonBirthDate() {
        return personBirthDate;
    }

    public BookViewResponseDto setPersonBirthDate(LocalDate personBirthDate) {
        this.personBirthDate = personBirthDate;
        return this;
    }

    public String getAuthorExpertise() {
        return authorExpertise;
    }

    public BookViewResponseDto setAuthorExpertise(String authorExpertise) {
        this.authorExpertise = authorExpertise;
        return this;
    }

    public String getAuthorWritingStyle() {
        return authorWritingStyle;
    }

    public BookViewResponseDto setAuthorWritingStyle(String authorWritingStyle) {
        this.authorWritingStyle = authorWritingStyle;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public BookViewResponseDto setLibraryName(String libraryName) {
        this.libraryName = libraryName;
        return this;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public BookViewResponseDto setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
        return this;
    }

    public String getLibraryOwnership() {
        return libraryOwnership;
    }

    public BookViewResponseDto setLibraryOwnership(String libraryOwnership) {
        this.libraryOwnership = libraryOwnership;
        return this;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public BookViewResponseDto setPublisherName(String publisherName) {
        this.publisherName = publisherName;
        return this;
    }

    public String getPublisherTypeName() {
        return publisherTypeName;
    }

    public BookViewResponseDto setPublisherTypeName(String publisherTypeName) {
        this.publisherTypeName = publisherTypeName;
        return this;
    }
}