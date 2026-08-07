package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.TranslatorRequestDto;
import com.example.library.project.dto.responses.TranslatorResponseDto;
import com.example.library.project.repositories.TranslatorRepository;
import com.example.library.project.services.interfaces.TranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    private final TranslatorRepository translatorRepository;

    public TranslatorServiceImpl(TranslatorRepository translatorRepository) {
        this.translatorRepository = translatorRepository;
    }

    @Override
    public TranslatorResponseDto save(TranslatorRequestDto translatorRequestDto) throws Exception {
        return null;
    }

    @Override
    public TranslatorResponseDto update(TranslatorRequestDto translatorRequestDto) throws Exception {
        return null;
    }

    @Override
    public TranslatorResponseDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<TranslatorResponseDto> findAll() {
        return List.of();
    }
}