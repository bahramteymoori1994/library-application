package com.example.library.project.controllers;

import com.example.library.project.dto.requests.AuthorRequestDto;
import com.example.library.project.dto.responses.AuthorResponseDto;
import com.example.library.project.dto.responses.AuthorTypeResponseDto;
import com.example.library.project.dto.responses.PersonResponseDto;
import com.example.library.project.dto.views.AuthorViewResponseDto;
import com.example.library.project.services.interfaces.AuthorService;
import com.example.library.project.services.interfaces.AuthorTypeService;
import com.example.library.project.services.interfaces.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorTypeService authorTypeService;

    public AuthorController(AuthorService authorService, AuthorTypeService authorTypeService) {
        this.authorService = authorService;
        this.authorTypeService = authorTypeService;
    }

    @GetMapping
    public String getAllAuthorTypes(Model model){

        List<AuthorResponseDto> authors = findAllAuthors();

        model.addAttribute("authors", authors);
        model.addAttribute("authorDto", new AuthorRequestDto());

        return "author";
    }

    @PostMapping("/saveAuthor")
    public String saveAuthor(@ModelAttribute("authorDto") AuthorRequestDto authorRequestDto, RedirectAttributes redirectAttributes) {
        try {
            authorService.save(authorRequestDto);
            redirectAttributes.addFlashAttribute("message", "نویسنده با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/author";
    }


    @PutMapping("/updateAuthor")
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponseDto update(@RequestBody AuthorRequestDto authorRequestDto) throws Exception {
        return authorService.update(authorRequestDto);
    }

    @GetMapping("/findAuthorById/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public AuthorResponseDto findAuthorTypeById(@PathVariable Long id) throws Exception {
        return authorService.findById(id);
    }

    @GetMapping("/findAllAuthors")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorResponseDto> findAllAuthors(){
        return authorService.findAll();
    }

    @GetMapping("/findAllAuthorsView")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorViewResponseDto> findAllAuthorsView(){
        return authorService.findAllUsersView();
    }

    @GetMapping("/findAllAuthorTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorTypeResponseDto> findAllAuthorTypes(){
        return authorTypeService.findAll();
    }
}