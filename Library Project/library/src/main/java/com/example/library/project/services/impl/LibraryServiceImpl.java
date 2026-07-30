package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.LibraryRequestDto;
import com.example.library.project.dto.responses.LibraryResponseDto;
import com.example.library.project.dto.views.LibraryViewResponseDto;
import com.example.library.project.model.entities.Library;
import com.example.library.project.model.views.LibraryView;
import com.example.library.project.repositories.LibraryRepository;
import com.example.library.project.services.interfaces.LibraryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public LibraryResponseDto save(LibraryRequestDto libraryRequestDto) throws Exception {

        LibraryResponseDto libraryResponseDto = new LibraryResponseDto();
        Library library = new Library();

        libraryRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( libraryRequestDto == null ){
            throw new Exception("Library request object is null");
        }

        BeanUtils.copyProperties(libraryRequestDto, library);

        Library librarySaved = libraryRepository.saveAndFlush(library);

        if( librarySaved == null ){
            throw new Exception("Library saved is null");
        }

        BeanUtils.copyProperties(librarySaved, libraryResponseDto);
        return libraryResponseDto;
    }

    @Override
    public LibraryResponseDto update(LibraryRequestDto libraryRequestDto) throws Exception {

        LibraryResponseDto libraryResponseDto = new LibraryResponseDto();
        Library library = new Library();

        libraryRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( libraryRequestDto == null ){
            throw new Exception("Library request object is null");
        }

        BeanUtils.copyProperties(libraryRequestDto, library);

        Library libraryUpdated = libraryRepository.save(library);

        if( libraryUpdated == null ){
            throw new Exception("Library updated is null");
        }

        BeanUtils.copyProperties(libraryUpdated, libraryResponseDto);
        return libraryResponseDto;
    }

    @Override
    public LibraryResponseDto findById(Long id) throws Exception {

        LibraryResponseDto libraryResponseDto = new LibraryResponseDto();
        Library findLibraryById = libraryRepository.findById(id).orElse(null);

        if( findLibraryById == null ){
            throw new Exception("Library id not found");
        }

        BeanUtils.copyProperties(findLibraryById, libraryResponseDto);
        return libraryResponseDto;
    }

    @Override
    public List<LibraryResponseDto> findAll() {

        List<LibraryResponseDto> libraryResponseDtoList = new ArrayList<>();
        List<Library> findLibraries = libraryRepository.findAll();

        findLibraries.stream()
                .forEach(library -> {
                    LibraryResponseDto libraryResponseDto = new LibraryResponseDto();
                    BeanUtils.copyProperties(library, libraryResponseDto);
                    libraryResponseDtoList.add(libraryResponseDto);
                });

        return libraryResponseDtoList;
    }

    @Override
    public List<LibraryViewResponseDto> findAllLibraries() {

        List<LibraryViewResponseDto> libraryViewResponseDtoList = new ArrayList<>();
        List<LibraryView> findLibraries = libraryRepository.findAllLibraries();

        findLibraries.stream()
                .forEach(library -> {
                    LibraryViewResponseDto libraryViewResponseDto = new LibraryViewResponseDto();
                    BeanUtils.copyProperties(library, libraryViewResponseDto);
                    libraryViewResponseDtoList.add(libraryViewResponseDto);
                });

        return libraryViewResponseDtoList;
    }
}