package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.BookSubjectRequestDto;
import com.example.library.project.dto.responses.BookSubjectResponseDto;
import com.example.library.project.model.entities.BookSubject;
import com.example.library.project.model.entities.User;
import com.example.library.project.repositories.BookSubjectRepository;
import com.example.library.project.services.interfaces.BookSubjectService;
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
public class BookSubjectServiceImpl implements BookSubjectService {

    private final BookSubjectRepository bookSubjectRepository;
    public final String CACHE_NAME = "bookSubject";

    public BookSubjectServiceImpl(BookSubjectRepository bookSubjectRepository) {
        this.bookSubjectRepository = bookSubjectRepository;
    }

    @Override
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public BookSubjectResponseDto save(BookSubjectRequestDto bookSubjectRequestDto) throws Exception {

        BookSubject bookSubject = new BookSubject();
        BookSubjectResponseDto bookSubjectResponseDto = new BookSubjectResponseDto();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( bookSubjectRequestDto == null )
        {
            throw new Exception("Book subject request object is null");
        }

        if (bookSubjectRequestDto.getBookSubject() != null && bookSubjectRequestDto.getBookSubject().getBookSubjectId() != null)
        {
            BookSubject bookSubjectParent = new BookSubject();
            bookSubjectParent.setBookSubjectId(bookSubjectRequestDto.getBookSubject().getBookSubjectId());
            bookSubject.setBookSubject(bookSubjectParent);
        }

        if( user != null )
        {
            bookSubjectRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        BeanUtils.copyProperties(bookSubjectRequestDto, bookSubject);

        BookSubject bookSubjectSaved = bookSubjectRepository.saveAndFlush(bookSubject);

        if( bookSubjectSaved == null )
        {
            throw new Exception("Book subject saved object is null");
        }

        BeanUtils.copyProperties(bookSubjectResponseDto, bookSubjectSaved);
        return  bookSubjectResponseDto;
    }

    @Override
    @CacheEvict(value = CACHE_NAME, allEntries = true)
    public BookSubjectResponseDto update(BookSubjectRequestDto bookSubjectRequestDto) throws Exception {

        BookSubject bookSubject = new BookSubject();
        BookSubjectResponseDto bookSubjectResponseDto = new BookSubjectResponseDto();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( bookSubjectRequestDto == null )
        {
            throw new Exception("Book subject request object is null");
        }

        if (bookSubjectRequestDto.getBookSubject() != null && bookSubjectRequestDto.getBookSubject().getBookSubjectId() != null)
        {
            BookSubject bookSubjectParent = new BookSubject();
            bookSubjectParent.setBookSubjectId(bookSubjectRequestDto.getBookSubject().getBookSubjectId());
            bookSubject.setBookSubject(bookSubjectParent);
        }

        if( user != null )
        {
            bookSubjectRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername());
        }

        BeanUtils.copyProperties(bookSubjectRequestDto, bookSubject);

        BookSubject bookSubjectUpdated = bookSubjectRepository.save(bookSubject);

        if( bookSubjectUpdated == null )
        {
            throw new Exception("Book subject updated object is null");
        }

        BeanUtils.copyProperties(bookSubjectResponseDto, bookSubjectUpdated);
        return  bookSubjectResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public BookSubjectResponseDto findById(Long id) throws Exception {

        BookSubjectResponseDto bookSubjectResponseDto = new BookSubjectResponseDto();
        BookSubject findBookSubjectById = bookSubjectRepository.findById(id).orElse(null);

        if( findBookSubjectById == null )
        {
            throw new Exception("Book subject id not found");
        }

        BeanUtils.copyProperties(findBookSubjectById, bookSubjectResponseDto);
        return  bookSubjectResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<BookSubjectResponseDto> findAll() {

        List<BookSubjectResponseDto> bookSubjectResponseDtoList = new ArrayList<>();
        List<BookSubject> bookSubjectList = bookSubjectRepository.findAll();

        bookSubjectList.stream()
                .forEach(bookSubject ->
                {
                    BookSubjectResponseDto bookSubjectResponseDto = new BookSubjectResponseDto();
                    BeanUtils.copyProperties(bookSubject, bookSubjectResponseDto);
                    bookSubjectResponseDtoList.add(bookSubjectResponseDto);
                });

        return bookSubjectResponseDtoList;
    }
}