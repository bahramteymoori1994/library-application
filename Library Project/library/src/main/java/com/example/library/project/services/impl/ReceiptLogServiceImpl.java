package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
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

        ReceiptLogResponseDto receiptLogResponseDto = new ReceiptLogResponseDto();
        ReceiptLog findReceiptLobbyLog = receiptLogRepository.findById(id).orElse(null);

        if( findReceiptLobbyLog == null ){
            throw new Exception("receiptLog id not found");
        }

        BeanUtils.copyProperties(findReceiptLobbyLog, receiptLogResponseDto);
        return receiptLogResponseDto;
    }

    @Override
    public ReceiptLogResponseDto findReceiptLogByReceipt(Long receiptId) throws Exception {

        ReceiptLogResponseDto receiptLogResponseDto = new ReceiptLogResponseDto();
        ReceiptLog findReceiptLogByReceipt = receiptLogRepository.findReceiptLogByReceipt(receiptId).orElse(null);

        if( findReceiptLogByReceipt == null ){
            throw new Exception("Receipt id not found");
        }

        BeanUtils.copyProperties(findReceiptLogByReceipt, receiptLogResponseDto);
        return receiptLogResponseDto;
    }

    @Override
    public List<ReceiptLogResponseDto> findAll() {
        return List.of();
    }
}