package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.AuthorTypeRequestDto;
import com.example.library.project.dto.responses.AuthorTypeResponseDto;
import com.example.library.project.model.entities.AuthorType;
import com.example.library.project.model.entities.User;
import com.example.library.project.repositories.AuthorTypeRepository;
import com.example.library.project.services.interfaces.AuthorTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorTypeServiceImpl implements AuthorTypeService {

    private final AuthorTypeRepository authorTypeRepository;
    private final String CACHE_NAME = "authorType";

    public AuthorTypeServiceImpl(AuthorTypeRepository authorTypeRepository) {
        this.authorTypeRepository = authorTypeRepository;
    }

    @Override
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public AuthorTypeResponseDto save(AuthorTypeRequestDto authorTypeRequestDto) throws Exception {

        AuthorTypeResponseDto authorTypeResponseDto = new AuthorTypeResponseDto();
        AuthorType authorType = new AuthorType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            authorTypeRequestDto
                                .setCreatedDate(LocalDate.now())
                                .setCreatedTime(LocalTime.now())
                                .setCreatedBy(user.getUsername());
        }

        if( authorTypeRequestDto == null ){
            throw new Exception("Author Type request object is null");
        }

        BeanUtils.copyProperties(authorTypeRequestDto, authorType);

        AuthorType authorTypeSaved = authorTypeRepository.save(authorType);

        if( authorTypeSaved == null ){
            throw new Exception("Author type saved is null");
        }

        BeanUtils.copyProperties(authorTypeSaved, authorTypeResponseDto);
        return authorTypeResponseDto;
    }

    @Override
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public AuthorTypeResponseDto update(AuthorTypeRequestDto authorTypeRequestDto) throws Exception {

        AuthorTypeResponseDto authorTypeResponseDto = new AuthorTypeResponseDto();
        AuthorType authorType = new AuthorType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            authorTypeRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        if( authorTypeRequestDto == null ){
            throw new Exception("Author Type request object is null");
        }

        BeanUtils.copyProperties(authorTypeRequestDto, authorType);

        AuthorType authorTypeUpdated = authorTypeRepository.save(authorType);

        if( authorTypeUpdated == null ){
            throw new Exception("Author type updated is null");
        }

        BeanUtils.copyProperties(authorTypeUpdated, authorTypeResponseDto);
        return authorTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public AuthorTypeResponseDto findById(Long id) throws Exception {

        AuthorTypeResponseDto authorTypeResponseDto = new AuthorTypeResponseDto();
        AuthorType findAuthorTypeById = authorTypeRepository.findById(id).orElse(null);

        if( findAuthorTypeById == null ){
            throw new Exception("Author type id not found");
        }

        BeanUtils.copyProperties(findAuthorTypeById, authorTypeResponseDto);
        return authorTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<AuthorTypeResponseDto> findAll() {

        List<AuthorTypeResponseDto> authorTypeResponseDtoList = new ArrayList<>();
        List<AuthorType> findAuthorTypes = authorTypeRepository.findAll();

        findAuthorTypes.stream()
                .forEach(authorType -> {
                    AuthorTypeResponseDto authorTypeResponseDto = new AuthorTypeResponseDto();
                    BeanUtils.copyProperties(authorType, authorTypeResponseDto);
                    authorTypeResponseDtoList.add(authorTypeResponseDto);
                });

        return authorTypeResponseDtoList;
    }
}