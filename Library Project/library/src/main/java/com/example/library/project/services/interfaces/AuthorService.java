package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.AuthorRequestDto;
import com.example.library.project.dto.responses.AuthorResponseDto;
import com.example.library.project.dto.views.AuthorViewResponseDto;

import java.util.List;

public interface AuthorService extends AbstractBaseService<AuthorRequestDto, AuthorResponseDto>{

    List<AuthorViewResponseDto> findAllUsersView();
}