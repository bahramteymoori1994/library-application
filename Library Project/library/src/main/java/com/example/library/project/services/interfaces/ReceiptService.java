package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.dto.views.ReceiptViewResponseDto;

import java.util.List;

public interface ReceiptService extends AbstractBaseService<ReceiptRequestDto, ReceiptResponseDto>{

    List<ReceiptViewResponseDto> findAllReceiptsView();
    List<ReceiptViewResponseDto> findAllReceiptsViewByUsername(String username);
}