package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.TranslatorRequestDto;
import com.example.library.project.dto.responses.TranslatorResponseDto;
import com.example.library.project.model.entities.Translator;
import com.example.library.project.repositories.TranslatorRepository;
import com.example.library.project.services.interfaces.TranslatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    private final TranslatorRepository translatorRepository;

    public TranslatorServiceImpl(TranslatorRepository translatorRepository) {
        this.translatorRepository = translatorRepository;
    }

    @Override
    public TranslatorResponseDto save(TranslatorRequestDto translatorRequestDto) throws Exception {

        Translator translator = new Translator();
        TranslatorResponseDto translatorResponseDto = new TranslatorResponseDto();

        if( translatorRequestDto == null )
        {
            throw new Exception("Translator object request is null");
        }

        translatorRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        BeanUtils.copyProperties(translatorRequestDto, translator);

        Translator translatorSaved = translatorRepository.saveAndFlush(translator);

        if( translatorSaved == null )
        {
            throw new Exception("Translator save object request is null");
        }

        BeanUtils.copyProperties(translatorResponseDto, translatorSaved);
        return translatorResponseDto;
    }

    @Override
    public TranslatorResponseDto update(TranslatorRequestDto translatorRequestDto) throws Exception {

        Translator translator = new Translator();
        TranslatorResponseDto translatorResponseDto = new TranslatorResponseDto();

        if( translatorRequestDto == null )
        {
            throw new Exception("Translator object request is null");
        }

        translatorRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        BeanUtils.copyProperties(translatorRequestDto, translator);

        Translator translatorUpdated = translatorRepository.save(translator);

        if( translatorUpdated == null )
        {
            throw new Exception("Translator updated object request is null");
        }

        BeanUtils.copyProperties(translatorResponseDto, translatorUpdated);
        return translatorResponseDto;
    }

    @Override
    public TranslatorResponseDto findById(Long id) throws Exception {

        TranslatorResponseDto  translatorResponseDto = new TranslatorResponseDto();
        Translator translator = translatorRepository.findById(id).orElse(null);

        if( translator == null )
        {
            throw new Exception("Translator id not found");
        }

        BeanUtils.copyProperties(translatorResponseDto, translator);
        return translatorResponseDto;
    }

    @Override
    public List<TranslatorResponseDto> findAll() {

        List<Translator> translators = translatorRepository.findAll();
        List<TranslatorResponseDto> translatorResponseList = new ArrayList<>();

        translatorResponseList.stream()
                .forEach(translator ->
                {
                    TranslatorResponseDto translatorResponseDto = new TranslatorResponseDto();
                    BeanUtils.copyProperties(translator, translatorResponseDto);
                    translatorResponseList.add(translatorResponseDto);
                });

        return  translatorResponseList;
    }
}