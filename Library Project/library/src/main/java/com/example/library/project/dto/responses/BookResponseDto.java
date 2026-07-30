package com.example.library.project.dto.responses;

import com.example.library.project.model.entities.Author;
import com.example.library.project.model.entities.BookType;
import com.example.library.project.model.entities.Library;
import com.example.library.project.model.entities.Publisher;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BookResponseDto {

    private Long bookId;
    private String bookTitle;
    private String isbn;
    private LocalDate publishDate;
    private Integer bookCount;
    private List<Author> authors = new ArrayList<>();
    private List<Library> libraries = new ArrayList<>();
    private BookType bookType;
    private Publisher publisher;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String createdBy;

    public BookResponseDto() {
    }

    public BookResponseDto(Long bookId, String bookTitle, String isbn, LocalDate publishDate, Integer bookCount, List<Author> authors, List<Library> libraries, BookType bookType, Publisher publisher, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.publishDate = publishDate;
        this.bookCount = bookCount;
        this.authors = authors;
        this.libraries = libraries;
        this.bookType = bookType;
        this.publisher = publisher;
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

    public String getIsbn() {
        return isbn;
    }

    public BookResponseDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public BookResponseDto setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public BookResponseDto setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public BookResponseDto setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public BookResponseDto setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public BookType getBookType() {
        return bookType;
    }

    public BookResponseDto setBookType(BookType bookType) {
        this.bookType = bookType;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public BookResponseDto setPublisher(Publisher publisher) {
        this.publisher = publisher;
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