package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.*;
import com.example.library.project.model.enums.HistoricalPeriodLevel;
import com.example.library.project.model.enums.TranslateStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookResponseDto {

    private Long bookId;
    private String bookTitle;
    private TranslateStatus translateStatus;
    private HistoricalPeriodLevel historicalPeriodLevel;
    private String isbn;
    private String description;
    private Short publishYear;
    private Byte PublishNumber;
    private Short pageCount;
    private List<Author> authors = new ArrayList<>();
    private List<Translator> translators = new ArrayList<>();
    private List<Library> libraries = new ArrayList<>();
    private BookSubject bookSubject;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public BookResponseDto() {
    }

    public BookResponseDto(Long bookId, String bookTitle, TranslateStatus translateStatus, HistoricalPeriodLevel historicalPeriodLevel, String isbn, String description, Short publishYear, Byte publishNumber, Short pageCount, List<Author> authors, List<Translator> translators, List<Library> libraries, BookSubject bookSubject, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.translateStatus = translateStatus;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.isbn = isbn;
        this.description = description;
        this.publishYear = publishYear;
        PublishNumber = publishNumber;
        this.pageCount = pageCount;
        this.authors = authors;
        this.translators = translators;
        this.libraries = libraries;
        this.bookSubject = bookSubject;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.createdBy = createdBy;
    }

    public Long getBookId() {
        return bookId;
    }

    public BookResponseDto setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookResponseDto setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public TranslateStatus getTranslateStatus() {
        return translateStatus;
    }

    public BookResponseDto setTranslateStatus(TranslateStatus translateStatus) {
        this.translateStatus = translateStatus;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public BookResponseDto setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookResponseDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BookResponseDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public Short getPublishYear() {
        return publishYear;
    }

    public BookResponseDto setPublishYear(Short publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    public Byte getPublishNumber() {
        return PublishNumber;
    }

    public BookResponseDto setPublishNumber(Byte publishNumber) {
        PublishNumber = publishNumber;
        return this;
    }

    public Short getPageCount() {
        return pageCount;
    }

    public BookResponseDto setPageCount(Short pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public BookResponseDto setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public BookResponseDto setTranslators(List<Translator> translators) {
        this.translators = translators;
        return this;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public BookResponseDto setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public BookResponseDto setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookResponseDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public BookResponseDto setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public BookResponseDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}