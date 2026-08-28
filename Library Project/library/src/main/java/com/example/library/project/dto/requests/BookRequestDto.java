package com.example.library.project.dto.requests;

import com.example.library.project.model.entities.*;
import com.example.library.project.model.enums.HistoricalPeriodLevel;
import com.example.library.project.model.enums.TranslateStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookRequestDto {

    private Long bookId;
    private String bookTitle;
    private TranslateStatus translateStatus;
    private HistoricalPeriodLevel historicalPeriodLevel;
    private String isbn;
    private String description;
    private Short publishYear;
    private Byte publishNumber;
    private Short pageCount;
    private Publisher publisher;
    private List<Author> authors = new ArrayList<>();
    private List<Translator> translators = new ArrayList<>();
    private List<Library> libraries = new ArrayList<>();
    private BookSubject bookSubject;
    private Byte bookCount;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public BookRequestDto() {
    }

    public BookRequestDto(Long bookId, String bookTitle, TranslateStatus translateStatus, HistoricalPeriodLevel historicalPeriodLevel, String isbn, String description, Short publishYear, Byte publishNumber, Short pageCount, Publisher publisher, List<Author> authors, List<Translator> translators, List<Library> libraries, BookSubject bookSubject, Byte bookCount, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.translateStatus = translateStatus;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.isbn = isbn;
        this.description = description;
        this.publishYear = publishYear;
        this.publishNumber = publishNumber;
        this.pageCount = pageCount;
        this.publisher = publisher;
        this.authors = authors;
        this.translators = translators;
        this.libraries = libraries;
        this.bookSubject = bookSubject;
        this.bookCount = bookCount;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Byte getBookCount() {
        return bookCount;
    }

    public BookRequestDto setBookCount(Byte bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookRequestDto setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookRequestDto setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public TranslateStatus getTranslateStatus() {
        return translateStatus;
    }

    public BookRequestDto setTranslateStatus(TranslateStatus translateStatus) {
        this.translateStatus = translateStatus;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public BookRequestDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookRequestDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookRequestDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Short getPublishYear() {
        return publishYear;
    }

    public BookRequestDto setPublishYear(Short publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    public Byte getPublishNumber() {
        return publishNumber;
    }

    public BookRequestDto setPublishNumber(Byte publishNumber) {
        this.publishNumber = publishNumber;
        return this;
    }

    public Short getPageCount() {
        return pageCount;
    }

    public BookRequestDto setPageCount(Short pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public BookRequestDto setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public BookRequestDto setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public BookRequestDto setTranslators(List<Translator> translators) {
        this.translators = translators;
        return this;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public BookRequestDto setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public BookRequestDto setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookRequestDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookRequestDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookRequestDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}