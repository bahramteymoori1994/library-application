package com.example.library.project.controllers;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.services.interfaces.ReceiptLogService;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("receipt")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptLogService receiptLogService;

    public ReceiptController(ReceiptService receiptService, ReceiptLogService receiptLogService) {
        this.receiptService = receiptService;
        this.receiptLogService = receiptLogService;
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

    @PostMapping("/saveReceiptLog")
    public ReceiptLogResponseDto saveReceiptLog(ReceiptLogRequestDto receiptLogRequestDto) throws Exception {
        return receiptLogService.save(receiptLogRequestDto);
    }

    @GetMapping("/findReceiptLogs")
    public List<ReceiptLogResponseDto> findReceiptLogs() throws Exception {
        return receiptLogService.findAll();
    }
}