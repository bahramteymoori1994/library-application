package com.example.library.project.controllers;

import com.example.library.project.dto.requests.BookTypeRequestDto;
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
        model.addAttribute("bookTypeDto", new BookTypeRequestDto());

        return "bookType";
    }

    @PostMapping("/saveBookType")
    public String saveBookType(@ModelAttribute("bookTypeDto") BookTypeRequestDto bookTypeRequestDto, RedirectAttributes redirectAttributes) {
        try {
            bookTypeService.save(bookTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع کتاب با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/bookType";
    }


    @PutMapping("/updateBookType")
    @ResponseStatus(value = HttpStatus.OK)
    public BookTypeResponseDto update(@RequestBody BookTypeRequestDto bookTypeRequestDto) throws Exception {
        return bookTypeService.update(bookTypeRequestDto);
    }

    @GetMapping("/findBookTypeById/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public BookTypeResponseDto findBookTypeById(@PathVariable Long id) throws Exception {
        return bookTypeService.findById(id);
    }


    @GetMapping("/findAllBookTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookTypeResponseDto> findAllBookTypes(){
        return bookTypeService.findAll();
    }
}