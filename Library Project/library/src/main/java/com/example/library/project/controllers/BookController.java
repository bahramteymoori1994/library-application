package com.example.library.project.controllers;

import com.example.library.project.dto.requests.BookRequestDto;
import com.example.library.project.dto.responses.*;
import com.example.library.project.dto.views.BookViewResponseDto;
import com.example.library.project.services.interfaces.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PublisherService publisherService;
    private final AuthorService authorService;
    private final BookTypeService bookTypeService;
    private final LibraryService libraryService;
    private final BookSubjectService bookSubjectService;
    private final TranslatorService translatorService;

    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService, BookTypeService bookTypeService, LibraryService libraryService, BookSubjectService bookSubjectService, TranslatorService translatorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.bookTypeService = bookTypeService;
        this.libraryService = libraryService;
        this.bookSubjectService = bookSubjectService;
        this.translatorService = translatorService;
    }

    @GetMapping
    public String getAllBooks(Model model){

        List<BookViewResponseDto> books = findAllBooks();

        model.addAttribute("books", books);
        model.addAttribute("bookDto", new BookRequestDto());

        return "book";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("bookDto") BookRequestDto bookRequestDto, RedirectAttributes redirectAttributes) {
        try {
            bookService.save(bookRequestDto);
            redirectAttributes.addFlashAttribute("message", "کتاب با موفقیت ثبت شد.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + e.getMessage());
        }
        return "redirect:/book";
    }


    @PutMapping("/updateBook")
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponseDto update(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return bookService.update(bookRequestDto);
    }

    @GetMapping("/findBookById/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BookResponseDto findBookTypeById(@PathVariable Long id) throws Exception {
        return bookService.findById(id);
    }


    @GetMapping("/findAllBooks")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookViewResponseDto> findAllBooks(){
        return bookService.findAllBooksView();
    }

    @GetMapping("/findAllPublishers")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<PublisherResponseDto> findAllPublishers(){
        return publisherService.findAll();
    }

    @GetMapping("/findAllAuthors")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<AuthorResponseDto> findAllAuthors(){
        return authorService.findAll();
    }

    @GetMapping("/findAllBookTypes")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookTypeResponseDto> findAllBookTypes(){
        return bookTypeService.findAll();
    }

    @GetMapping("/findAllLibraries")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<LibraryResponseDto> findAllLibraries(){
        return libraryService.findAll();
    }

    @GetMapping("/findAllBookSubjects")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<BookSubjectResponseDto> findAllBookSubjects(){
        return bookSubjectService.findAll();
    }

    @GetMapping("/findAllTranslators")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public List<TranslatorResponseDto> findAllTranslators(){
        return translatorService.findAll();
    }
}