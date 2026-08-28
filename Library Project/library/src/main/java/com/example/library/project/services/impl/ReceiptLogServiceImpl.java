package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;
import com.example.library.project.model.entities.ReceiptLog;
import com.example.library.project.repositories.ReceiptLogRepository;
import com.example.library.project.services.interfaces.ReceiptLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ReceiptLogServiceImpl implements ReceiptLogService {

    private final ReceiptLogRepository receiptLogRepository;

    public ReceiptLogServiceImpl(ReceiptLogRepository receiptLogRepository) {
        this.receiptLogRepository = receiptLogRepository;
    }

    @Override
    public ReceiptLogResponseDto save(ReceiptLogRequestDto receiptLogRequestDto) throws Exception {

        ReceiptLog receiptLog = new ReceiptLog();
        ReceiptLogResponseDto receiptLogResponseDto = new ReceiptLogResponseDto();

        if( receiptLogRequestDto == null ){
            throw new Exception("receiptLog request object is null");
        }

        receiptLogRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin");

        BeanUtils.copyProperties(receiptLogRequestDto, receiptLog);
        ReceiptLog savedReceiptLog = receiptLogRepository.saveAndFlush(receiptLog);

        if( savedReceiptLog == null ){
            throw new Exception("receiptLog saved object is null");
        }

        BeanUtils.copyProperties(savedReceiptLog, receiptLogResponseDto);
        return receiptLogResponseDto;
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