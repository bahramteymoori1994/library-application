package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.BookRequestDto;
import com.example.library.project.dto.responses.BookResponseDto;
import com.example.library.project.model.entities.Book;
import com.example.library.project.repositories.BookRepository;
import com.example.library.project.services.interfaces.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
    public BookResponseDto save(BookRequestDto bookRequestDto) throws Exception {

        BookResponseDto bookResponseDto = new BookResponseDto();
        Book book = new Book();

        bookRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( bookRequestDto == null ){
            throw new Exception("Book request object is null");
        }

        BeanUtils.copyProperties(bookRequestDto, book);

        Book bookSaved = bookRepository.saveAndFlush(book);

        if( bookSaved == null ){
            throw new Exception("Book saved is null");
        }

        BeanUtils.copyProperties(bookSaved, bookResponseDto);
        return bookResponseDto;
    }

    @Override
    public BookResponseDto update(BookRequestDto bookRequestDto) throws Exception {

        BookResponseDto bookResponseDto = new BookResponseDto();
        Book book = new Book();

        bookRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( bookRequestDto == null ){
            throw new Exception("Book request object is null");
        }

        BeanUtils.copyProperties(bookRequestDto, book);

        Book bookUpdated = bookRepository.save(book);

        if( bookUpdated == null ){
            throw new Exception("Book updated is null");
        }

        BeanUtils.copyProperties(bookUpdated, bookResponseDto);
        return bookResponseDto;
    }

    @Override
    public BookResponseDto findById(Long id) throws Exception {

        BookResponseDto bookResponseDto = new BookResponseDto();
        Book findBookById = bookRepository.findById(id).orElse(null);

        if( findBookById == null ){
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
}