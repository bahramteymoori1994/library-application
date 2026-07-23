package com.example.library.project.controllers;

import com.example.library.project.dto.requests.PublisherTypeRequestDto;
import com.example.library.project.dto.responses.PublisherTypeResponseDto;
import com.example.library.project.services.interfaces.PublisherTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/publisherType")
public class PublisherTypeController {

    private final PublisherTypeService publisherTypeService;

    public PublisherTypeController(PublisherTypeService publisherTypeService) {
        this.publisherTypeService = publisherTypeService;
    }

    @GetMapping
    public String getAllPeople(Model model){

        List<PublisherTypeResponseDto> publisherTypes = findAllPublisherTypes();

        model.addAttribute("publisherTypes", publisherTypes);
        model.addAttribute("publisherTypeDto", new PublisherTypeRequestDto());

        return "publisherType";
    }

    @PostMapping("/savePublisherType")
    public String savePublisherType(@ModelAttribute("publisherTypeDto") PublisherTypeRequestDto publisherTypeRequestDto, RedirectAttributes redirectAttributes) {
        try {
            publisherTypeService.save(publisherTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع ناشر با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/publisherType";
    }


    @PutMapping("/updatePerson")
    @ResponseStatus(value = HttpStatus.OK)
    public PublisherTypeResponseDto update(@RequestBody PublisherTypeRequestDto publisherTypeRequestDto) throws Exception {
        return publisherTypeService.update(publisherTypeRequestDto);
    }

    @GetMapping("/findPublisherTypeById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public PublisherTypeResponseDto findPublisherTypeById(@PathVariable Long id) throws Exception {
        return publisherTypeService.findById(id);
    }

    @GetMapping("/findAllPublisherTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PublisherTypeResponseDto> findAllPublisherTypes(){
        return publisherTypeService.findAll();
    }


    @GetMapping("/findAllPeople")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PublisherTypeResponseDto> findAllPeople(){
        return publisherTypeService.findAll();
    }
}