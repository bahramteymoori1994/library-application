package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.AuthorRequestDto;
import com.example.library.project.dto.responses.AuthorResponseDto;
import com.example.library.project.dto.views.AuthorViewResponseDto;
import com.example.library.project.model.entities.Author;
import com.example.library.project.model.views.AuthorView;
import com.example.library.project.repositories.AuthorRepository;
import com.example.library.project.services.interfaces.AuthorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorResponseDto save(AuthorRequestDto authorRequestDto) throws Exception {

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        Author author = new Author();

        authorRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( authorRequestDto == null ){
            throw new Exception("Author request object is null");
        }

        BeanUtils.copyProperties(authorRequestDto, author);

        Author authorSaved = authorRepository.save(author);

        if( authorSaved == null ){
            throw new Exception("Author saved is null");
        }

        BeanUtils.copyProperties(authorSaved, authorResponseDto);
        return authorResponseDto;
    }

    @Override
    public AuthorResponseDto update(AuthorRequestDto authorRequestDto) throws Exception {

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        Author author = new Author();

        authorRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( authorRequestDto == null ){
            throw new Exception("Author request object is null");
        }

        BeanUtils.copyProperties(authorRequestDto, author);

        Author authorUpdated = authorRepository.save(author);

        if( authorUpdated == null ){
            throw new Exception("Author updated is null");
        }

        BeanUtils.copyProperties(authorUpdated, authorResponseDto);
        return authorResponseDto;
    }

    @Override
    public AuthorResponseDto findById(Long id) throws Exception {

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        Author findAuthorById = authorRepository.findById(id).orElse(null);

        if( findAuthorById == null ){
            throw new Exception("Author id not found");
        }

        BeanUtils.copyProperties(findAuthorById, authorResponseDto);
        return authorResponseDto;
    }

    @Override
    public List<AuthorResponseDto> findAll() {

        List<AuthorResponseDto> authorResponseDtoList = new ArrayList<>();
        List<Author> findAuthors = authorRepository.findAll();

        findAuthors.stream()
                .forEach(author -> {
                    AuthorResponseDto authorResponseDto = new AuthorResponseDto();
                    BeanUtils.copyProperties(author, authorResponseDto);
                    authorResponseDtoList.add(authorResponseDto);
                });

        return authorResponseDtoList;
    }

    @Override
    public List<AuthorViewResponseDto> findAllUsersView() {

        List<AuthorViewResponseDto> authorViewResponseDtoList = new ArrayList<>();
        List<AuthorView> findAuthorsView = authorRepository.findAllAuthorsView();

        findAuthorsView.stream()
                .forEach(author -> {
                    AuthorViewResponseDto authorViewResponseDto = new AuthorViewResponseDto();
                    BeanUtils.copyProperties(author, authorViewResponseDto);
                    authorViewResponseDtoList.add(authorViewResponseDto);
                });

        return authorViewResponseDtoList;
    }
}