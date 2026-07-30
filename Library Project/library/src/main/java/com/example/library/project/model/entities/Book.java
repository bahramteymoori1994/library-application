package com.example.library.project.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Column(name = "BOOK_TITLE", columnDefinition = "nvarchar(50)", nullable = false)
    @NotNull(message = "Book title is required")
    private String bookTitle;

    @Column(name = "ISBN", columnDefinition = "nvarchar(10)", nullable = false, unique = true)
    @NotNull(message = "ISBN is required")
    private String isbn;

    @Column(name = "PUBLISH_DATE", columnDefinition = "date", nullable = false)
    @NotNull(message = "Publish date is required")
    private LocalDate publishDate;

    @Column(name = "BOOK_COUNT", nullable = false)
    @NotNull(message = "Book count is required")
    private Integer bookCount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",
    foreignKey = @ForeignKey(name = "FK_BOOK_ID")), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID",
    referencedColumnName = "AUTHOR_ID"), foreignKey = @ForeignKey(name = "FK_AUTHOR_ID"))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "library_book", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",
            foreignKey = @ForeignKey(name = "FK_BOOK_ID")), inverseJoinColumns = @JoinColumn(name = "LIBRARY_ID",
            referencedColumnName = "LIBRARY_ID"), foreignKey = @ForeignKey(name = "FK_LIBRARY_ID"))
    private List<Library> libraries = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "BOOK_TYPE_ID")
    private BookType bookType;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @Column(name = "CREATED_DATE", nullable = false, columnDefinition = "date")
    @NotNull(message = "Created Date is required")
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", nullable = false, columnDefinition = "time")
    @NotNull(message = "Created Time is required")
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    @NotNull(message = "Created By is required")
    private String createdBy;

    public Book() {
    }

    public Book(Long bookId, String bookTitle, String isbn, LocalDate publishDate, Integer bookCount, List<Author> authors, List<Library> libraries, BookType bookType, Publisher publisher, LocalDate createdDate, LocalTime createdTime, String createdBy) {
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

    public Book setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public Book setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Book setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public Book setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public Book setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public BookType getBookType() {
        return bookType;
    }

    public Book setBookType(BookType bookType) {
        this.bookType = bookType;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Book setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Book setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalTime getCreatedTime() {
        return createdTime;
    }

    public Book setCreatedTime(LocalTime createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Book setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishDate=" + publishDate +
                ", bookCount=" + bookCount +
                ", authors=" + authors +
                ", libraries=" + libraries +
                ", bookType=" + bookType +
                ", publisher=" + publisher +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}