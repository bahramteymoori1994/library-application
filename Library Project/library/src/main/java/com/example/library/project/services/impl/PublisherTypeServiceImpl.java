package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.PublisherTypeRequestDto;
import com.example.library.project.dto.responses.PublisherTypeResponseDto;
import com.example.library.project.model.entities.PublisherType;
import com.example.library.project.repositories.PublisherTypeRepository;
import com.example.library.project.services.interfaces.PublisherTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherTypeServiceImpl implements PublisherTypeService {

    private final PublisherTypeRepository publisherTypeRepository;
    private final String CACHE_NAME = "publisherType";

    public PublisherTypeServiceImpl(PublisherTypeRepository publisherTypeRepository) {
        this.publisherTypeRepository = publisherTypeRepository;
    }

    @Override
    public PublisherTypeResponseDto save(PublisherTypeRequestDto publisherTypeRequestDto) throws Exception {

        PublisherTypeResponseDto publisherTypeResponseDto = new PublisherTypeResponseDto();
        PublisherType publisherType = new PublisherType();

        publisherTypeRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( publisherTypeRequestDto == null ){
            throw new Exception("Publisher type request object is null");
        }

        BeanUtils.copyProperties(publisherTypeRequestDto, publisherType);

        PublisherType publisherTypeSaved = publisherTypeRepository.save(publisherType);

        if( publisherTypeSaved == null ){
            throw new Exception("Publisher type saved object is null");
        }

        BeanUtils.copyProperties(publisherTypeSaved, publisherTypeResponseDto);
        return publisherTypeResponseDto;
    }

    @Override
    public PublisherTypeResponseDto update(PublisherTypeRequestDto publisherTypeRequestDto) throws Exception {

        PublisherTypeResponseDto publisherTypeResponseDto = new PublisherTypeResponseDto();
        PublisherType publisherType = new PublisherType();

        publisherTypeRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( publisherTypeRequestDto == null ){
            throw new Exception("Publisher type request object is null");
        }

        BeanUtils.copyProperties(publisherTypeRequestDto, publisherType);

        PublisherType publisherTypeUpdated = publisherTypeRepository.save(publisherType);

        if( publisherTypeUpdated == null ){
            throw new Exception("Publisher type updated object is null");
        }

        BeanUtils.copyProperties(publisherTypeUpdated, publisherTypeResponseDto);
        return publisherTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public PublisherTypeResponseDto findById(Long id) throws Exception {

        PublisherTypeResponseDto publisherTypeResponseDto = new PublisherTypeResponseDto();
        PublisherType findPublisherTypeById = publisherTypeRepository.findById(id).orElse(null);

        if( findPublisherTypeById == null ){
            throw new Exception("Publisher type id not found");
        }

        BeanUtils.copyProperties(findPublisherTypeById, publisherTypeResponseDto);
        return publisherTypeResponseDto;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<PublisherTypeResponseDto> findAll() {

        List<PublisherTypeResponseDto> publisherTypeResponseDtoList = new ArrayList<>();
        List<PublisherType> findAllPublisherTypes = publisherTypeRepository.findAll();

        findAllPublisherTypes.stream()
                .forEach(publisherType -> {
                    PublisherTypeResponseDto publisherTypeResponseDto = new PublisherTypeResponseDto();
                    BeanUtils.copyProperties(publisherType, publisherTypeResponseDto);
                    publisherTypeResponseDtoList.add(publisherTypeResponseDto);
                });

        return publisherTypeResponseDtoList;
    }
}