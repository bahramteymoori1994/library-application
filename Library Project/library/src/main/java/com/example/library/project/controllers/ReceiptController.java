package com.example.library.project.controllers;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.BookResponseDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.services.interfaces.BookService;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final BookService bookService;

    public ReceiptController(ReceiptService receiptService, BookService bookService) {
        this.receiptService = receiptService;
        this.bookService = bookService;
    }

    @GetMapping
    public String showReceiptPage(Model model) throws Exception {
        List<ReceiptResponseDto> receipts = receiptService.findAll();
        model.addAttribute("receipts", receipts);
        model.addAttribute("receiptDto", new ReceiptRequestDto());
        return "receipt";
    }

    @GetMapping("/list")
    public String findReceipts(Model model) throws Exception {
        List<ReceiptResponseDto> receipts = receiptService.findAll();
        model.addAttribute("receipts", receipts);
        model.addAttribute("receiptDto", new ReceiptRequestDto());
        return "receipt";
    }

    @PostMapping("/saveReceipt")
    public String save(@ModelAttribute("receiptDto") ReceiptRequestDto receiptRequestDto,
                       RedirectAttributes redirectAttributes) {
        try {
            receiptService.save(receiptRequestDto);
            redirectAttributes.addFlashAttribute("message", "رسید با موفقیت ثبت شد.");
        } catch (Exception exception) {
            redirectAttributes.addFlashAttribute("message", "خطا در ثبت: " + exception.getMessage());
        }
        return "redirect:/receipt";
    }

    @PutMapping("/updateReceipt")
    @ResponseBody
    public ReceiptResponseDto updateReceipt(@RequestBody ReceiptRequestDto receiptRequestDto) throws Exception {
        return receiptService.update(receiptRequestDto);
    }

    @GetMapping("/findReceiptById/{id}")
    @ResponseBody
    public ReceiptResponseDto findReceiptById(@PathVariable Long id) throws Exception {
        return receiptService.findById(id);
    }

    @GetMapping("/findAllBooks")
    @ResponseBody
    public List<BookResponseDto> findAllBooks() {
        return bookService.findAll();
    }
}