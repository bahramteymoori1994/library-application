package com.example.library.project.controllers;

import com.example.library.project.dto.requests.LibraryRequestDto;
import com.example.library.project.dto.responses.LibraryResponseDto;
import com.example.library.project.services.interfaces.LibraryService;
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

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping
    public String getAllLibraries(Model model){

        List<LibraryResponseDto> libraries = findAllLibraries();

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
    @ResponseStatus(value = HttpStatus.OK)
    public LibraryResponseDto findLibraryById(@PathVariable Long id) throws Exception {
        return libraryService.findById(id);
    }


    @GetMapping("/findAllLibraries")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<LibraryResponseDto> findAllLibraries(){
        return libraryService.findAll();
    }
}