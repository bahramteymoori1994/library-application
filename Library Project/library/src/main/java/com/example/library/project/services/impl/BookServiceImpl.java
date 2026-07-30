package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.BookRequestDto;
import com.example.library.project.dto.responses.BookResponseDto;
import com.example.library.project.dto.views.BookViewResponseDto;
import com.example.library.project.model.entities.*;
import com.example.library.project.model.views.BookView;
import com.example.library.project.repositories.BookRepository;
import com.example.library.project.services.interfaces.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookResponseDto save(BookRequestDto bookRequestDto) throws Exception {

        if (bookRequestDto == null) {
            throw new Exception("Book request object is null");
        }

        Book book = new Book();

        // کپی کردن فیلدهای ساده
        book.setBookTitle(bookRequestDto.getBookTitle());
        book.setIsbn(bookRequestDto.getIsbn());
        book.setPublishDate(bookRequestDto.getPublishDate());
        book.setBookCount(bookRequestDto.getBookCount());

        // تنظیم Publisher از روی Object دریافتی
        if (bookRequestDto.getPublisher() != null && bookRequestDto.getPublisher().getPublisherId() != null) {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(bookRequestDto.getPublisher().getPublisherId());
            book.setPublisher(publisher);
        }

        // تنظیم BookType از روی Object دریافتی
        if (bookRequestDto.getBookType() != null && bookRequestDto.getBookType().getBookTypeId() != null) {
            BookType bookType = new BookType();
            bookType.setBookTypeId(bookRequestDto.getBookType().getBookTypeId());
            book.setBookType(bookType);
        }

        // تنظیم Authors از روی List دریافتی
        if (bookRequestDto.getAuthors() != null && !bookRequestDto.getAuthors().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            for (Author authorDto : bookRequestDto.getAuthors()) {
                if (authorDto.getAuthorId() != null) {
                    Author author = new Author();
                    author.setAuthorId(authorDto.getAuthorId());
                    authors.add(author);
                }
            }
            book.setAuthors(authors);
        }

        // تنظیم Libraries از روی List دریافتی
        if (bookRequestDto.getLibraries() != null && !bookRequestDto.getLibraries().isEmpty()) {
            List<Library> libraries = new ArrayList<>();
            for (Library libraryDto : bookRequestDto.getLibraries()) {
                if (libraryDto.getLibraryId() != null) {
                    Library library = new Library();
                    library.setLibraryId(libraryDto.getLibraryId());
                    libraries.add(library);
                }
            }
            book.setLibraries(libraries);
        }

        // تنظیم تاریخ و زمان
        book.setCreatedDate(LocalDate.now());
        book.setCreatedTime(LocalTime.now());
        book.setCreatedBy("admin");

        Book bookSaved = bookRepository.saveAndFlush(book);

        if (bookSaved == null) {
            throw new Exception("Book saved is null");
        }

        BookResponseDto bookResponseDto = new BookResponseDto();
        BeanUtils.copyProperties(bookSaved, bookResponseDto);
        return bookResponseDto;
    }

    @Override
    @Transactional
    public BookResponseDto update(BookRequestDto bookRequestDto) throws Exception {

        if (bookRequestDto == null) {
            throw new Exception("Book request object is null");
        }

        // پیدا کردن کتاب موجود
        Book existingBook = bookRepository.findById(bookRequestDto.getBookId())
                .orElseThrow(() -> new Exception("Book not found"));

        // به‌روزرسانی فیلدها
        existingBook.setBookTitle(bookRequestDto.getBookTitle());
        existingBook.setIsbn(bookRequestDto.getIsbn());
        existingBook.setPublishDate(bookRequestDto.getPublishDate());
        existingBook.setBookCount(bookRequestDto.getBookCount());

        // به‌روزرسانی Publisher
        if (bookRequestDto.getPublisher() != null && bookRequestDto.getPublisher().getPublisherId() != null) {
            Publisher publisher = new Publisher();
            publisher.setPublisherId(bookRequestDto.getPublisher().getPublisherId());
            existingBook.setPublisher(publisher);
        } else {
            existingBook.setPublisher(null);
        }

        // به‌روزرسانی BookType
        if (bookRequestDto.getBookType() != null && bookRequestDto.getBookType().getBookTypeId() != null) {
            BookType bookType = new BookType();
            bookType.setBookTypeId(bookRequestDto.getBookType().getBookTypeId());
            existingBook.setBookType(bookType);
        } else {
            existingBook.setBookType(null);
        }

        // به‌روزرسانی Authors
        if (bookRequestDto.getAuthors() != null && !bookRequestDto.getAuthors().isEmpty()) {
            List<Author> authors = new ArrayList<>();
            for (Author authorDto : bookRequestDto.getAuthors()) {
                if (authorDto.getAuthorId() != null) {
                    Author author = new Author();
                    author.setAuthorId(authorDto.getAuthorId());
                    authors.add(author);
                }
            }
            existingBook.setAuthors(authors);
        } else {
            existingBook.setAuthors(new ArrayList<>());
        }

        // به‌روزرسانی Libraries
        if (bookRequestDto.getLibraries() != null && !bookRequestDto.getLibraries().isEmpty()) {
            List<Library> libraries = new ArrayList<>();
            for (Library libraryDto : bookRequestDto.getLibraries()) {
                if (libraryDto.getLibraryId() != null) {
                    Library library = new Library();
                    library.setLibraryId(libraryDto.getLibraryId());
                    libraries.add(library);
                }
            }
            existingBook.setLibraries(libraries);
        } else {
            existingBook.setLibraries(new ArrayList<>());
        }

        Book bookUpdated = bookRepository.save(existingBook);

        if (bookUpdated == null) {
            throw new Exception("Book updated is null");
        }

        BookResponseDto bookResponseDto = new BookResponseDto();
        BeanUtils.copyProperties(bookUpdated, bookResponseDto);
        return bookResponseDto;
    }

    @Override
    public BookResponseDto findById(Long id) throws Exception {

        BookResponseDto bookResponseDto = new BookResponseDto();
        Book findBookById = bookRepository.findById(id).orElse(null);

        if (findBookById == null) {
            throw new Exception("Book id not found");
        }

        BeanUtils.copyProperties(findBookById, bookResponseDto);
        return bookResponseDto;
    }

    @Override
    public List<BookResponseDto> findAll() {

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        List<Book> findBooks = bookRepository.findAll();

        findBooks.stream()
                .forEach(book -> {
                    BookResponseDto bookResponseDto = new BookResponseDto();
                    BeanUtils.copyProperties(book, bookResponseDto);
                    bookResponseDtoList.add(bookResponseDto);
                });

        return bookResponseDtoList;
    }

    @Override
    public List<BookViewResponseDto> findAllBooksView() {

        List<BookViewResponseDto> bookViewResponseDtoList = new ArrayList<>();
        List<BookView> findBooks = bookRepository.findAllBooksView();

        findBooks.stream()
                .forEach(bookView -> {
                    BookViewResponseDto bookViewResponseDto = new BookViewResponseDto();

                    // کپی کردن فیلدهای ساده
                    bookViewResponseDto.setBookId(bookView.getBookId());
                    bookViewResponseDto.setBookTitle(bookView.getBookTitle());
                    bookViewResponseDto.setBookIsbn(bookView.getBookIsbn());
                    bookViewResponseDto.setBookCount(bookView.getBookCount());
                    bookViewResponseDto.setBookPublishDate(bookView.getBookPublishDate());
                    bookViewResponseDto.setCreatedDate(bookView.getCreatedDate());
                    bookViewResponseDto.setCreatedBy(bookView.getCreatedBy());
                    bookViewResponseDto.setBookTypeSubject(bookView.getBookTypeSubject());
                    bookViewResponseDto.setBookTypeLanguage(bookView.getBookTypeLanguage());
                    bookViewResponseDto.setAuthorFirstName(bookView.getAuthorFirstName());
                    bookViewResponseDto.setAuthorLastName(bookView.getAuthorLastName());
                    bookViewResponseDto.setPersonBirthDate(bookView.getPersonBirthDate());
                    bookViewResponseDto.setAuthorExpertise(bookView.getAuthorExpertise());
                    bookViewResponseDto.setAuthorWritingStyle(bookView.getAuthorWritingStyle());
                    bookViewResponseDto.setLibraryName(bookView.getLibraryName());
                    bookViewResponseDto.setLibraryCity(bookView.getLibraryCity());
                    bookViewResponseDto.setLibraryOwnership(bookView.getLibraryOwnership());
                    bookViewResponseDto.setPublisherName(bookView.getPublisherName());
                    bookViewResponseDto.setPublisherTypeName(bookView.getPublisherTypeName());

                    // تنظیم لیست نویسندگان (برای نمایش در جدول)
                    List<String> authorNames = new ArrayList<>();
                    if (bookView.getAuthorFirstName() != null && bookView.getAuthorLastName() != null) {
                        authorNames.add(bookView.getAuthorFirstName() + " " + bookView.getAuthorLastName());
                    }
                    bookViewResponseDto.setAuthorNames(authorNames);

                    // تنظیم لیست کتابخانه‌ها (برای نمایش در جدول)
                    List<String> libraryNames = new ArrayList<>();
                    if (bookView.getLibraryName() != null) {
                        libraryNames.add(bookView.getLibraryName());
                    }
                    bookViewResponseDto.setLibraryNames(libraryNames);

                    bookViewResponseDtoList.add(bookViewResponseDto);
                });

        return bookViewResponseDtoList;
    }
}