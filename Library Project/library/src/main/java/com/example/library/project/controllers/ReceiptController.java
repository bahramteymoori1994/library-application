package com.example.library.project.controllers;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.BookResponseDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.dto.views.ReceiptViewResponseDto;
import com.example.library.project.services.interfaces.BookService;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
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
    public String showReceiptPage(Model model, Principal principal, Authentication authentication) {

        String currentUsername = principal != null ? principal.getName() : "";
        boolean isAdminOrLibrarian = false;

        if (authentication != null && authentication.getAuthorities() != null) {
            isAdminOrLibrarian = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                            || a.getAuthority().equals("ROLE_LIBRARIAN"));
        }

        List<ReceiptViewResponseDto> receipts;

        if (isAdminOrLibrarian) {
            // ادمین و کتابدار → همه رسیدها
            receipts = receiptService.findAllReceiptsView();
        } else {
            // کاربر عادی → فقط رسیدهای خودش
            receipts = receiptService.findAllReceiptsViewByUsername(currentUsername);
        }

        model.addAttribute("receipts", receipts);
        model.addAttribute("receiptDto", new ReceiptRequestDto());
        model.addAttribute("currentUsername", currentUsername);
        model.addAttribute("isAdminOrLibrarian", isAdminOrLibrarian);   // برای استفاده در فرانت

        return "receipt";
    }

    @GetMapping("/list")
    public String findReceipts(Model model) throws Exception {
        List<ReceiptViewResponseDto> receipts = findAllReceiptsView();
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

    @GetMapping("/findAllReceiptsView")
    @ResponseBody
    public List<ReceiptViewResponseDto> findAllReceiptsView()
    {
        return receiptService.findAllReceiptsView();
    }

    @GetMapping("/findAllReceiptsView/{username}")
    @ResponseBody
    public List<ReceiptViewResponseDto> findAllReceiptsViewByUsername(@PathVariable String username)
    {
        return receiptService.findAllReceiptsViewByUsername(username);
    }

    @PostMapping("/reject")
    @ResponseBody
    public ReceiptResponseDto rejectReceipt(@RequestBody ReceiptResponseDto receiptResponseDto) throws Exception {

        receiptService.rejectAction(receiptResponseDto);
        return receiptService.findById(receiptResponseDto.getReceiptId());
    }
}