package com.example.library.project.controllers;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/saveReceipt")
    public ReceiptResponseDto saveReceipt(ReceiptRequestDto receiptRequestDto) throws Exception {
        return receiptService.save(receiptRequestDto);
    }

    @PutMapping("/updateReceipt")
    public ReceiptResponseDto updateReceipt(ReceiptRequestDto receiptRequestDto) throws Exception {
        return receiptService.update(receiptRequestDto);
    }

    @GetMapping("/findReceiptById/{id}")
    public ReceiptResponseDto findReceiptById(@PathVariable Long id) throws Exception {
        return receiptService.findById(id);
    }

    @GetMapping("/findReceipts")
    public List<ReceiptResponseDto> findReceipts() throws Exception {
        return receiptService.findAll();
    }
}