package com.example.library.project.controllers;

import com.example.library.project.dto.requests.LibraryRequestDto;
import com.example.library.project.dto.responses.LibraryResponseDto;
import com.example.library.project.dto.responses.LibraryTypeResponseDto;
import com.example.library.project.dto.views.LibraryViewResponseDto;
import com.example.library.project.services.interfaces.LibraryService;
import com.example.library.project.services.interfaces.LibraryTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;
    private final LibraryTypeService libraryTypeService;

    public LibraryController(LibraryService libraryService, LibraryTypeService libraryTypeService) {
        this.libraryService = libraryService;
        this.libraryTypeService = libraryTypeService;
    }

    @GetMapping
    public String getAllLibraries(Model model){

        List<LibraryViewResponseDto> libraries = findAllLibraries();

        model.addAttribute("libraries", libraries);
        model.addAttribute("libraryDto", new LibraryRequestDto());

        return "library";
    }

    @PostMapping("/saveLibrary")
    public String saveLibrary(@ModelAttribute("libraryDto") LibraryRequestDto libraryRequestDto, RedirectAttributes redirectAttributes) {
        try {
            libraryService.save(libraryRequestDto);
            redirectAttributes.addFlashAttribute("message", "کتاب خانه با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/library";
    }


    @PutMapping("/updateLibrary")
    @ResponseStatus(value = HttpStatus.OK)
    public LibraryResponseDto update(@RequestBody LibraryRequestDto libraryRequestDto) throws Exception {
        return libraryService.update(libraryRequestDto);
    }

    @GetMapping("/findLibraryById/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public LibraryResponseDto findLibraryById(@PathVariable Long id) throws Exception {
        return libraryService.findById(id);
    }

    @GetMapping("/findAllLibraryTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<LibraryTypeResponseDto> findAllLibraryTypes(){
        return libraryTypeService.findAll();
    }

    @GetMapping("/findAllLibraries")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<LibraryViewResponseDto> findAllLibraries(){
        return libraryService.findAllLibraries();
    }
}