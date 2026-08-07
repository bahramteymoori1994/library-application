package com.example.library.project.controllers;

import com.example.library.project.dto.requests.BookSubjectRequestDto;
import com.example.library.project.dto.responses.BookSubjectResponseDto;
import com.example.library.project.services.interfaces.BookSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/bookSubject")
public class BookSubjectController {

    private final BookSubjectService bookSubjectService;

    public BookSubjectController(BookSubjectService bookSubjectService) {
        this.bookSubjectService = bookSubjectService;
    }

    @GetMapping
    public String getAllBookSubjects(Model model){

        List<BookSubjectResponseDto> bookSubjects = findAllBookSubjects();

        model.addAttribute("bookSubjects", bookSubjects);
        model.addAttribute("bookSubjectDto", new BookSubjectRequestDto());

        return "bookSubject";
    }

    @PostMapping("/saveBookSubject")
    public String saveBookSubject(@ModelAttribute("bookSubjectDto") BookSubjectRequestDto bookSubjectRequestDto, RedirectAttributes redirectAttributes) {
        try {
            bookSubjectService.save(bookSubjectRequestDto);
            redirectAttributes.addFlashAttribute("message", "موضوع کتاب با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/bookSubject";
    }


    @PutMapping("/updateBookSubject")
    @ResponseStatus(value = HttpStatus.OK)
    public BookSubjectResponseDto update(@RequestBody BookSubjectRequestDto bookSubjectRequestDto) throws Exception {
        return bookSubjectService.update(bookSubjectRequestDto);
    }

    @GetMapping("/findBookSubjectById/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BookSubjectResponseDto findBookSubjectById(@PathVariable Long id) throws Exception {
        return bookSubjectService.findById(id);
    }


    @GetMapping("/findAllBookSubjects")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookSubjectResponseDto> findAllBookSubjects(){
        return bookSubjectService.findAll();
    }
}