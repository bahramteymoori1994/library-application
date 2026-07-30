package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.BookRequestDto;
import com.example.library.project.dto.responses.BookResponseDto;
import com.example.library.project.dto.views.BookViewResponseDto;

import java.util.List;

public interface BookService extends AbstractBaseService<BookRequestDto, BookResponseDto>{

    List<BookViewResponseDto> findAllBooksView();
}