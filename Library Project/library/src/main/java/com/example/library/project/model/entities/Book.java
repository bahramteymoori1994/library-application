package com.example.library.project.model.entities;

import com.example.library.project.model.enums.HistoricalPeriodLevel;
import com.example.library.project.model.enums.TranslateStatus;
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

    @Column(name = "BOOK_TITLE", columnDefinition = "nvarchar(50)", nullable = false, unique = true)
    @NotNull(message = "Book title is required")
    private String bookTitle;

    @Column(name = "TRASNLATE_STATUS", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TranslateStatus translateStatus;

    @Column(name = "HISTORICAL_PERIOD_LEVEL",  nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Historical period level is required")
    private HistoricalPeriodLevel historicalPeriodLevel;

    @Column(name = "ISBN", columnDefinition = "varchar(10)", nullable = false, unique = true)
    @NotNull(message = "Isbn is required")
    private String isbn;

    @Column(name = "BOOK_DESCRIPTION", columnDefinition = "nvarchar(2000)")
    private String description;

    @Column(name = "PUBLISH_YEAR", nullable = false)
    @NotNull(message = "Publish year is required")
    private Short publishYear;

    @Column(name = "PUBLISH_NUMBER", nullable = false)
    @NotNull(message = "Publish number is required")
    private Byte publishNumber;

    @Column(name = "PAGE_COUNT", nullable = false)
    @NotNull(message = "Page count is required")
    private Short pageCount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",
    foreignKey = @ForeignKey(name = "FK_BOOK_AUTHOR_ID")), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID",
    referencedColumnName = "AUTHOR_ID", foreignKey = @ForeignKey(name = "FK_AUTHOR_ID")))
    private List<Author> authors = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "translator_book", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",
            foreignKey = @ForeignKey(name = "FK_TRANSLATOR_BOOK_ID")), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID",
            referencedColumnName = "AUTHOR_ID", foreignKey = @ForeignKey(name = "FK_TRANSLATOR_ID")))
    private List<Translator> translators = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "library_book", joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID",
            foreignKey = @ForeignKey(name = "FK_BOOK_LIBRARY_ID")), inverseJoinColumns = @JoinColumn(name = "LIBRARY_ID",
            referencedColumnName = "LIBRARY_ID", foreignKey = @ForeignKey(name = "FK_LIBRARY_ID")))
    private List<Library> libraries = new ArrayList<>();

    @Column(name = "BOOK_COUNT", nullable = false)
    @NotNull(message = "Book count is required")
    private Byte bookCount;

    @ManyToOne
    @JoinColumn(name = "PUBLISHER_ID")
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "BOOK_SUBJECT_ID")
    private BookSubject bookSubject;

    @Column(name = "CREATED_DATE", columnDefinition = "date", nullable = false)
    private LocalDate createdDate;

    @Column(name = "CREATED_TIME", columnDefinition = "time", nullable = false)
    private LocalTime createdTime;

    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "varchar(50)")
    private String createdBy;

    public Book() {
    }

    public Book(Long bookId, String bookTitle, TranslateStatus translateStatus, HistoricalPeriodLevel historicalPeriodLevel, String isbn, String description, Short publishYear, Byte publishNumber, Short pageCount, List<Author> authors, List<Translator> translators, List<Library> libraries, Byte bookCount, Publisher publisher, BookSubject bookSubject, LocalDate createdDate, LocalTime createdTime, String createdBy) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.translateStatus = translateStatus;
        this.historicalPeriodLevel = historicalPeriodLevel;
        this.isbn = isbn;
        this.description = description;
        this.publishYear = publishYear;
        this.publishNumber = publishNumber;
        this.pageCount = pageCount;
        this.authors = authors;
        this.translators = translators;
        this.libraries = libraries;
        this.bookCount = bookCount;
        this.publisher = publisher;
        this.bookSubject = bookSubject;
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

    public TranslateStatus getTranslateStatus() {
        return translateStatus;
    }

    public Book setTranslateStatus(TranslateStatus translateStatus) {
        this.translateStatus = translateStatus;
        return this;
    }

    public HistoricalPeriodLevel getHistoricalPeriodLevel() {
        return historicalPeriodLevel;
    }

    public Book setHistoricalPeriodLevel(HistoricalPeriodLevel historicalPeriodLevel) {
        this.historicalPeriodLevel = historicalPeriodLevel;
        return this;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public Short getPublishYear() {
        return publishYear;
    }

    public Book setPublishYear(Short publishYear) {
        this.publishYear = publishYear;
        return this;
    }

    public Byte getPublishNumber() {
        return publishNumber;
    }

    public Book setPublishNumber(Byte publishNumber) {
        this.publishNumber = publishNumber;
        return this;
    }

    public Short getPageCount() {
        return pageCount;
    }

    public Book setPageCount(Short pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public Book setAuthors(List<Author> authors) {
        this.authors = authors;
        return this;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public Book setTranslators(List<Translator> translators) {
        this.translators = translators;
        return this;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public Book setLibraries(List<Library> libraries) {
        this.libraries = libraries;
        return this;
    }

    public Byte getBookCount() {
        return bookCount;
    }

    public Book setBookCount(Byte bookCount) {
        this.bookCount = bookCount;
        return this;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Book setPublisher(Publisher publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookSubject getBookSubject() {
        return bookSubject;
    }

    public Book setBookSubject(BookSubject bookSubject) {
        this.bookSubject = bookSubject;
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
                ", translateStatus=" + translateStatus +
                ", historicalPeriodLevel=" + historicalPeriodLevel +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", publishYear=" + publishYear +
                ", PublishNumber=" + publishNumber +
                ", pageCount=" + pageCount +
                ", authors=" + authors +
                ", translators=" + translators +
                ", libraries=" + libraries +
                ", bookCount=" + bookCount +
                ", publisher=" + publisher +
                ", bookSubject=" + bookSubject +
                ", createdDate=" + createdDate +
                ", createdTime=" + createdTime +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}