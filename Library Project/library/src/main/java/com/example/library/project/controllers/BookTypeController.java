package com.example.library.project.controllers;

import com.example.library.project.dto.requests.BookTypeRequestDto;
import com.example.library.project.dto.requests.PersonRequestDto;
import com.example.library.project.dto.responses.BookTypeResponseDto;
import com.example.library.project.services.interfaces.BookTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bookType")
public class BookTypeController {

    private final BookTypeService bookTypeService;

    public BookTypeController(BookTypeService bookTypeService) {
        this.bookTypeService = bookTypeService;
    }

    @GetMapping
    public String getAllBookTypes(Model model){

        List<BookTypeResponseDto> bookTypes = findAllBookTypes();

        model.addAttribute("bookTypes", bookTypes);
        model.addAttribute("bookTypeDto", new PersonRequestDto());

        return "bookType";
    }

    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("bookTypeDto") BookTypeRequestDto bookTypeRequestDto, RedirectAttributes redirectAttributes) {
        try {
            bookTypeService.save(bookTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع کتاب با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/person";
    }


    @PutMapping("/updateBookType")
    @ResponseStatus(value = HttpStatus.OK)
    public BookTypeResponseDto update(@RequestBody BookTypeRequestDto bookTypeRequestDto) throws Exception {
        return bookTypeService.update(bookTypeRequestDto);
    }

    @GetMapping("/findPersonById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookTypeResponseDto findPersonById(@PathVariable Long id) throws Exception {
        return bookTypeService.findById(id);
    }


    @GetMapping("/findAllPeople")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookTypeResponseDto> findAllBookTypes(){
        return bookTypeService.findAll();
    }
}