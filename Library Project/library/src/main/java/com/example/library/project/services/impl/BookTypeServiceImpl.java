package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.BookTypeRequestDto;
import com.example.library.project.dto.responses.BookTypeResponseDto;
import com.example.library.project.model.entities.BookType;
import com.example.library.project.model.entities.User;
import com.example.library.project.repositories.BookTypeRepository;
import com.example.library.project.services.interfaces.BookTypeService;
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
public class BookTypeServiceImpl implements BookTypeService {

    private final BookTypeRepository bookTypeRepository;
    private final String CACHE_NAME = "bookType";

    public BookTypeServiceImpl(BookTypeRepository bookTypeRepository) {
        this.bookTypeRepository = bookTypeRepository;
    }

    @Override
    public BookTypeResponseDto save(BookTypeRequestDto bookTypeRequestDto) throws Exception {

        BookTypeResponseDto bookTypeResponseDto = new BookTypeResponseDto();
        BookType bookType = new BookType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            bookTypeRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        if( bookTypeRequestDto == null ){
            throw new Exception("Book Type request object is null");
        }

        BeanUtils.copyProperties(bookTypeRequestDto, bookType);

        BookType bookTypeSaved = bookTypeRepository.save(bookType);

        if( bookTypeSaved == null ){
            throw new Exception("Book type saved is null");
        }

        BeanUtils.copyProperties(bookTypeSaved, bookTypeResponseDto);
        return bookTypeResponseDto;
    }

    @Override
    public BookTypeResponseDto update(BookTypeRequestDto bookTypeRequestDto) throws Exception {

        BookTypeResponseDto bookTypeResponseDto = new BookTypeResponseDto();
        BookType bookType = new BookType();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            bookTypeRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        if( bookTypeRequestDto == null ){
            throw new Exception("Book Type request object is null");
        }

        BeanUtils.copyProperties(bookTypeRequestDto, bookType);

        BookType bookTypeUpdated = bookTypeRepository.save(bookType);

        if( bookTypeUpdated == null ){
            throw new Exception("Book type updated is null");
        }

        BeanUtils.copyProperties(bookTypeUpdated, bookTypeResponseDto);
        return bookTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public BookTypeResponseDto findById(Long id) throws Exception {

        BookTypeResponseDto bookTypeResponseDto = new BookTypeResponseDto();
        BookType findBookTypeById = bookTypeRepository.findById(id).orElse(null);

        if( findBookTypeById == null ){
            throw new Exception("Book type id not found");
        }

        BeanUtils.copyProperties(findBookTypeById, bookTypeResponseDto);
        return bookTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<BookTypeResponseDto> findAll() {

        List<BookTypeResponseDto> bookTypeResponseDtoList = new ArrayList<>();
        List<BookType> findBookTypes = bookTypeRepository.findAll();

        findBookTypes.stream()
                .forEach(bookType -> {
                    BookTypeResponseDto bookTypeResponseDto = new BookTypeResponseDto();
                    BeanUtils.copyProperties(bookType, bookTypeResponseDto);
                    bookTypeResponseDtoList.add(bookTypeResponseDto);
                });

        return bookTypeResponseDtoList;
    }
}