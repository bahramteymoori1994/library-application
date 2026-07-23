package com.example.library.project.controllers;

import com.example.library.project.dto.requests.PublisherRequestDto;
import com.example.library.project.dto.responses.PublisherResponseDto;
import com.example.library.project.dto.responses.PublisherTypeResponseDto;
import com.example.library.project.services.interfaces.PublisherService;
import com.example.library.project.services.interfaces.PublisherTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherService publisherService;
    private final PublisherTypeService publisherTypeService;

    public PublisherController(PublisherService publisherService, PublisherTypeService publisherTypeService) {
        this.publisherService = publisherService;
        this.publisherTypeService = publisherTypeService;
    }

    @GetMapping
    public String getAllPublishers(Model model) throws Exception {

        List<PublisherResponseDto> publishers = findAll();

        model.addAttribute("publishers", publishers);
        model.addAttribute("publisherDto", new PublisherRequestDto());

        return "publisher";
    }

    @PostMapping("/savePublisher")
    public String savePerson(@ModelAttribute("publisherDto") PublisherRequestDto publisherRequestDto, RedirectAttributes redirectAttributes) {
        try {
            publisherService.save(publisherRequestDto);
            redirectAttributes.addFlashAttribute("message", "ناشر با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/publisher";
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

    @GetMapping("findAllPublisherTypes")
    public List<PublisherTypeResponseDto> findAllPublisherTypes(){
        return publisherTypeService.findAll();
    }
}