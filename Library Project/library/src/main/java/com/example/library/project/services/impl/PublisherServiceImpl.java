package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.PublisherRequestDto;
import com.example.library.project.dto.responses.PublisherResponseDto;
import com.example.library.project.dto.views.PublisherViewResponseDto;
import com.example.library.project.model.entities.Publisher;
import com.example.library.project.model.views.PublisherView;
import com.example.library.project.repositories.PublisherRepository;
import com.example.library.project.services.interfaces.PublisherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public PublisherResponseDto save(PublisherRequestDto publisherRequestDto) throws Exception {

        PublisherResponseDto response = new PublisherResponseDto();
        Publisher publisher = new Publisher();

        publisherRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( publisherRequestDto == null ){
            throw new Exception("Publisher request object is null");
        }

        publisher.setCreatedDate(LocalDate.now());
        publisher.setCreatedTime(LocalTime.now());
        publisher.setCreatedBy("admin");

        BeanUtils.copyProperties(publisherRequestDto, publisher);
        Publisher publisherSaved = publisherRepository.saveAndFlush(publisher);

        if( publisherSaved == null ){
            throw new Exception("Publisher saved is null");
        }

        BeanUtils.copyProperties(publisherSaved, response);
        return response;
    }

    @Override
    public PublisherResponseDto update(PublisherRequestDto publisherRequestDto) throws Exception {

        PublisherResponseDto response = new PublisherResponseDto();
        Publisher publisher = new Publisher();

        publisherRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        if( publisherRequestDto == null ){
            throw new Exception("Publisher request object is null");
        }

        publisher.setCreatedDate(LocalDate.now());
        publisher.setCreatedTime(LocalTime.now());
        publisher.setCreatedBy("admin");

        BeanUtils.copyProperties(publisherRequestDto, publisher);
        Publisher publisherSaved = publisherRepository.saveAndFlush(publisher);

        if( publisherSaved == null ){
            throw new Exception("Publisher saved is null");
        }

        BeanUtils.copyProperties(publisherSaved, response);
        return response;
    }

    @Override
    public PublisherResponseDto findById(Long id) throws Exception {

        PublisherResponseDto publisherResponseDto = new PublisherResponseDto();
        Publisher findPublisherById = publisherRepository.findById(id).orElse(null);

        if( findPublisherById == null ){
            throw new Exception("publisher id not found");
        }

        BeanUtils.copyProperties(findPublisherById, publisherResponseDto);
        return publisherResponseDto;
    }

    @Override
    public List<PublisherResponseDto> findAll() {

        List<PublisherResponseDto> publisherResponseDtoList = new ArrayList<>();
        List<Publisher> findAllPublishers = publisherRepository.findAll();

        findAllPublishers.stream()
                .forEach(publisher -> {
                    PublisherResponseDto publisherResponseDto = new PublisherResponseDto();
                    BeanUtils.copyProperties(publisher, publisherResponseDto);
                    publisherResponseDtoList.add(publisherResponseDto);
                });

        return publisherResponseDtoList;
    }

    @Override
    public List<PublisherViewResponseDto> findAllPublishersView() {

        List<PublisherViewResponseDto> publisherViewResponseDtoList = new ArrayList<>();
        List<PublisherView> findAllPublishers = publisherRepository.findAllPublishersView();

        findAllPublishers.stream()
                .forEach(publisher -> {
                    PublisherViewResponseDto publisherViewResponseDto = new PublisherViewResponseDto();
                    BeanUtils.copyProperties(publisher, publisherViewResponseDto);
                    publisherViewResponseDtoList.add(publisherViewResponseDto);
                });

        return publisherViewResponseDtoList;
    }
}