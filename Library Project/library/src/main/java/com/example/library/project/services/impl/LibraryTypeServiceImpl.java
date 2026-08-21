package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.LibraryTypeRequestDto;
import com.example.library.project.dto.responses.LibraryTypeResponseDto;
import com.example.library.project.model.entities.LibraryType;
import com.example.library.project.model.entities.User;
import com.example.library.project.repositories.LibraryTypeRepository;
import com.example.library.project.services.interfaces.LibraryTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibraryTypeServiceImpl implements LibraryTypeService {

    private final LibraryTypeRepository libraryTypeRepository;
    private final String CACHE_NAME = "libraryType";

    public LibraryTypeServiceImpl(LibraryTypeRepository libraryTypeRepository) {
        this.libraryTypeRepository = libraryTypeRepository;
    }

    @Override
    public LibraryTypeResponseDto save(LibraryTypeRequestDto libraryTypeRequestDto) throws Exception {

        LibraryTypeResponseDto libraryTypeResponseDto = new LibraryTypeResponseDto();
        LibraryType libraryType = new LibraryType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            libraryTypeRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        if( libraryTypeRequestDto == null ){
            throw new Exception("Library Type request object is null");
        }

        BeanUtils.copyProperties(libraryTypeRequestDto, libraryType);

        LibraryType libraryTypeSaved = libraryTypeRepository.save(libraryType);

        if( libraryTypeSaved == null ){
            throw new Exception("Library type saved is null");
        }

        BeanUtils.copyProperties(libraryTypeSaved, libraryTypeResponseDto);
        return libraryTypeResponseDto;
    }

    @Override
    public LibraryTypeResponseDto update(LibraryTypeRequestDto libraryTypeRequestDto) throws Exception {

        LibraryTypeResponseDto libraryTypeResponseDto = new LibraryTypeResponseDto();
        LibraryType libraryType = new LibraryType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            libraryTypeRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        if( libraryTypeRequestDto == null ){
            throw new Exception("Library Type request object is null");
        }

        BeanUtils.copyProperties(libraryTypeRequestDto, libraryType);

        LibraryType libraryTypeUpdated = libraryTypeRepository.save(libraryType);

        if( libraryTypeUpdated == null ){
            throw new Exception("Library type updated is null");
        }

        BeanUtils.copyProperties(libraryTypeUpdated, libraryTypeResponseDto);
        return libraryTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public LibraryTypeResponseDto findById(Long id) throws Exception {

        LibraryTypeResponseDto libraryTypeResponseDto = new LibraryTypeResponseDto();
        LibraryType findLibraryTypeById = libraryTypeRepository.findById(id).orElse(null);

        if( findLibraryTypeById == null ){
            throw new Exception("Library type id not found");
        }

        BeanUtils.copyProperties(findLibraryTypeById, libraryTypeResponseDto);
        return libraryTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<LibraryTypeResponseDto> findAll() {

        List<LibraryTypeResponseDto> libraryTypeResponseDtoList = new ArrayList<>();
        List<LibraryType> findLibraryTypes = libraryTypeRepository.findAll();

        findLibraryTypes.stream()
                .forEach(libraryType -> {
                    LibraryTypeResponseDto libraryTypeResponseDto = new LibraryTypeResponseDto();
                    BeanUtils.copyProperties(libraryType, libraryTypeResponseDto);
                    libraryTypeResponseDtoList.add(libraryTypeResponseDto);
                });

        return libraryTypeResponseDtoList;
    }
}