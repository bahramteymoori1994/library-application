package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.PublisherRequestDto;
import com.example.library.project.dto.responses.PublisherResponseDto;
import com.example.library.project.dto.views.PublisherViewResponseDto;

import java.util.List;

public interface PublisherService extends AbstractBaseService<PublisherRequestDto, PublisherResponseDto> {

    List<PublisherViewResponseDto> findAllPublishersView();
}