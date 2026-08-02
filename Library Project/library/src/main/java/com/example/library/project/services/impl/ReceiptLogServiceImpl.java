package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;
import com.example.library.project.repositories.ReceiptLogRepository;
import com.example.library.project.services.interfaces.ReceiptLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptLogServiceImpl implements ReceiptLogService {

    private final ReceiptLogRepository receiptLogRepository;

    public ReceiptLogServiceImpl(ReceiptLogRepository receiptLogRepository) {
        this.receiptLogRepository = receiptLogRepository;
    }

    @Override
    public ReceiptLogResponseDto save(ReceiptLogRequestDto receiptLogRequestDto) throws Exception {
        return null;
    }

    @Override
    public ReceiptLogResponseDto update(ReceiptLogRequestDto receiptLogRequestDto) throws Exception {
        return null;
    }

    @Override
    public ReceiptLogResponseDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<ReceiptLogResponseDto> findAll() {
        return List.of();
    }
}