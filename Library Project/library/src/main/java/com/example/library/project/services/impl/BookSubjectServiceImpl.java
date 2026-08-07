package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.BookSubjectRequestDto;
import com.example.library.project.dto.responses.BookSubjectResponseDto;
import com.example.library.project.repositories.BookSubjectRepository;
import com.example.library.project.services.interfaces.BookSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSubjectServiceImpl implements BookSubjectService {

    private final BookSubjectRepository bookSubjectRepository;

    public BookSubjectServiceImpl(BookSubjectRepository bookSubjectRepository) {
        this.bookSubjectRepository = bookSubjectRepository;
    }

    @Override
    public BookSubjectResponseDto save(BookSubjectRequestDto bookSubjectRequestDto) throws Exception {
        return null;
    }

    @Override
    public BookSubjectResponseDto update(BookSubjectRequestDto bookSubjectRequestDto) throws Exception {
        return null;
    }

    @Override
    public BookSubjectResponseDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<BookSubjectResponseDto> findAll() {
        return List.of();
    }
}