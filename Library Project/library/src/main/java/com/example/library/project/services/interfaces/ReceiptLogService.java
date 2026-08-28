package com.example.library.project.services.interfaces;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;

public interface ReceiptLogService extends AbstractBaseService<ReceiptLogRequestDto, ReceiptLogResponseDto>{

    ReceiptLogResponseDto findReceiptLogByReceipt(Long receiptId) throws Exception;
}