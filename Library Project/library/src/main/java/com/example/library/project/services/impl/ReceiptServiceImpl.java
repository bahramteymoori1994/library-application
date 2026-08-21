package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.model.entities.Book;
import com.example.library.project.model.entities.Receipt;
import com.example.library.project.model.enums.ReceiptStatus;
import com.example.library.project.repositories.ReceiptRepository;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public ReceiptResponseDto save(ReceiptRequestDto receiptRequestDto) throws Exception {

        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        Receipt receipt = new Receipt();
        List<Book> books = receiptRequestDto.getBooks();

        receiptRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin")
                .setReceiptStatus(ReceiptStatus.INIT_REGISTRATION);

        if( receiptRequestDto == null ){
            throw new Exception("Receipt request object is null");
        }

        if( books != null || books.size() > 0 )
        {
            List<Book> bookList = new ArrayList<>();

            books.stream()
                    .forEach(book ->
                    {
                        Book newBook = new Book();
                        book.setBookId(book.getBookId());
                        bookList.add(newBook);
                    });

            receipt.setBooks(bookList);
        }

        receiptRequestDto.setReceiptBookCount(receipt.getBooks().size());

        BeanUtils.copyProperties(receiptRequestDto, receipt);
        Receipt receiptSaved = receiptRepository.saveAndFlush(receipt);

        if( receiptSaved == null )
        {
            throw new Exception("Receipt saved object is null");
        }

        BeanUtils.copyProperties(receiptSaved, receiptResponseDto);
        return receiptResponseDto;
    }

    @Override
    public ReceiptResponseDto update(ReceiptRequestDto receiptRequestDto) throws Exception {

        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        Receipt receipt = new Receipt();
        List<Book> books = receiptResponseDto.getBooks();

        receiptRequestDto
                .setCreatedDate(LocalDate.now())
                .setCreatedTime(LocalTime.now())
                .setCreatedBy("admin")
                .setReceiptStatus(ReceiptStatus.INIT_REGISTRATION);

        if( receiptRequestDto == null )
        {
            throw new Exception("Receipt request object is null");
        }

        if( books != null || books.size() > 0 )
        {
            List<Book> bookList = new ArrayList<>();

            books.stream()
                    .forEach(book ->
                    {
                        Book newBook = new Book();
                        book.setBookId(book.getBookId());
                        bookList.add(newBook);
                    });

            receipt.setBooks(bookList);
        }

        BeanUtils.copyProperties(receiptRequestDto, receipt);
        Receipt receiptUpdated = receiptRepository.save(receipt);

        if( receiptUpdated == null )
        {
            throw new Exception("Receipt updated object is null");
        }

        BeanUtils.copyProperties(receiptUpdated, receiptResponseDto);
        return receiptResponseDto;
    }

    @Override
    public ReceiptResponseDto findById(Long id) throws Exception {

        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        Receipt receipt = receiptRepository.findById(id).orElse(null);

        if( receipt == null )
        {
            throw new Exception("Receipt id not found");
        }

        BeanUtils.copyProperties(receipt, receiptResponseDto);
        return receiptResponseDto;
    }

    @Override
    public List<ReceiptResponseDto> findAll() {

        List<ReceiptResponseDto> receiptResponseDtoList = new ArrayList<>();
        List<Receipt> receipts = receiptRepository.findAll();

        receipts.stream()
                .forEach(receipt ->
                {
                    ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
                    BeanUtils.copyProperties(receipt, receiptResponseDto);
                    receiptResponseDtoList.add(receiptResponseDto);
                });

        return receiptResponseDtoList;
    }
}