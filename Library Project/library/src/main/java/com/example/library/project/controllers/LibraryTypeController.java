package com.example.library.project.controllers;

import com.example.library.project.dto.requests.LibraryTypeRequestDto;
import com.example.library.project.dto.responses.LibraryTypeResponseDto;
import com.example.library.project.services.interfaces.LibraryTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/libraryType")
public class LibraryTypeController {

    private final LibraryTypeService libraryTypeService;

    public LibraryTypeController(LibraryTypeService libraryTypeService) {
        this.libraryTypeService = libraryTypeService;
    }

    @GetMapping
    public String getAllLibraryTypes(Model model){

        List<LibraryTypeResponseDto> libraryTypes = findAllLibraryTypes();

        model.addAttribute("libraryTypes", libraryTypes);
        model.addAttribute("libraryTypeDto", new LibraryTypeRequestDto());

        return "libraryType";
    }

    @PostMapping("/saveLibraryType")
    public String saveLibraryType(@ModelAttribute("libraryTypeDto") LibraryTypeRequestDto libraryTypeRequestDto, RedirectAttributes redirectAttributes) {
        try {
            libraryTypeService.save(libraryTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع کتاب خانه با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/libraryType";
    }


    @PutMapping("/updateLibraryType")
    @ResponseStatus(value = HttpStatus.OK)
    public LibraryTypeResponseDto update(@RequestBody LibraryTypeRequestDto libraryTypeRequestDto) throws Exception {
        return libraryTypeService.update(libraryTypeRequestDto);
    }

    @GetMapping("/findLibraryTypeById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public LibraryTypeResponseDto findLibraryTypeById(@PathVariable Long id) throws Exception {
        return libraryTypeService.findById(id);
    }


    @GetMapping("/findAllLibraryTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<LibraryTypeResponseDto> findAllLibraryTypes(){
        return libraryTypeService.findAll();
    }
}