package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.LibraryRequestDto;
import com.example.library.project.dto.responses.LibraryResponseDto;
import com.example.library.project.dto.views.LibraryViewResponseDto;

import java.util.List;

public interface LibraryService extends AbstractBaseService<LibraryRequestDto, LibraryResponseDto>{

    List<LibraryViewResponseDto> findAllLibraries();
}