package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.repositories.ReceiptRepository;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public ReceiptResponseDto save(ReceiptRequestDto receiptRequestDto) throws Exception {
        return null;
    }

    @Override
    public ReceiptResponseDto update(ReceiptRequestDto receiptRequestDto) throws Exception {
        return null;
    }

    @Override
    public ReceiptResponseDto findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<ReceiptResponseDto> findAll() {
        return List.of();
    }
}