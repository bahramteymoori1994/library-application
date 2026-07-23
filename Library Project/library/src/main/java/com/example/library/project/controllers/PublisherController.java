package com.example.library.project.controllers;

import com.example.library.project.dto.requests.PublisherRequestDto;
import com.example.library.project.dto.responses.PublisherResponseDto;
import com.example.library.project.services.interfaces.PublisherService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @PostMapping("/savePublisher")
    public PublisherResponseDto save(@RequestBody PublisherRequestDto publisherRequestDto) throws Exception {
        return publisherService.save(publisherRequestDto);
    }

    @PutMapping("/updatePublisher")
    public PublisherResponseDto update(@RequestBody PublisherRequestDto publisherRequestDto) throws Exception {
        return publisherService.update(publisherRequestDto);
    }

    @GetMapping("/findPublisherById/{id}")
    public PublisherResponseDto findPublisherById(@PathVariable Long id) throws Exception {
        return publisherService.findById(id);
    }

    @GetMapping("/findPublishers")
    public List<PublisherResponseDto> findAll() throws Exception {
        return publisherService.findAll();
    }
}