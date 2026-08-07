package com.example.library.project.controllers;

import com.example.library.project.dto.requests.AuthorTypeRequestDto;
import com.example.library.project.dto.responses.AuthorTypeResponseDto;
import com.example.library.project.services.interfaces.AuthorTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authorType")
public class AuthorTypeController {

    private final AuthorTypeService authorTypeService;

    public AuthorTypeController(AuthorTypeService authorTypeService) {
        this.authorTypeService = authorTypeService;
    }

    @GetMapping
    public String getAllAuthorTypes(Model model) {
        List<AuthorTypeResponseDto> authorTypes = findAllAuthorTypes();
        model.addAttribute("authorTypes", authorTypes);
        model.addAttribute("authorTypeDto", new AuthorTypeRequestDto());
        return "authorType";
    }

    @PostMapping("/saveAuthorType")
    public String saveAuthorType(@ModelAttribute("authorTypeDto") AuthorTypeRequestDto authorTypeRequestDto,
                                 RedirectAttributes redirectAttributes) {
        try {
            authorTypeService.save(authorTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع نویسنده با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/authorType";
    }

    @PostMapping("/updateAuthorType")
    public String updateAuthorType(@ModelAttribute("authorTypeDto") AuthorTypeRequestDto authorTypeRequestDto,
                                   RedirectAttributes redirectAttributes) {
        try {
            authorTypeService.update(authorTypeRequestDto);
            redirectAttributes.addFlashAttribute("message", "نوع نویسنده با موفقیت به‌روزرسانی شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در به‌روزرسانی: " + e.getMessage());
        }
        return "redirect:/authorType";
    }

    @GetMapping("/findAuthorTypeById/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorTypeResponseDto findAuthorTypeById(@PathVariable Long id) throws Exception {
        return authorTypeService.findById(id);
    }

    @GetMapping("/findAllAuthorTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorTypeResponseDto> findAllAuthorTypes() {
        return authorTypeService.findAll();
    }
}