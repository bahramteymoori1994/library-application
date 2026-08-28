package com.example.library.project.services.impl;

import com.example.library.project.dto.requests.ReceiptLogRequestDto;
import com.example.library.project.dto.requests.ReceiptRequestDto;
import com.example.library.project.dto.responses.ReceiptLogResponseDto;
import com.example.library.project.dto.responses.ReceiptResponseDto;
import com.example.library.project.dto.views.ReceiptViewResponseDto;
import com.example.library.project.model.entities.Book;
import com.example.library.project.model.entities.Receipt;
import com.example.library.project.model.entities.ReceiptLog;
import com.example.library.project.model.entities.User;
import com.example.library.project.model.enums.ReceiptStatus;
import com.example.library.project.model.views.ReceiptView;
import com.example.library.project.repositories.ReceiptRepository;
import com.example.library.project.services.interfaces.ReceiptLogService;
import com.example.library.project.services.interfaces.ReceiptService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ReceiptLogService receiptLogService;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ReceiptLogService receiptLogService) {
        this.receiptRepository = receiptRepository;
        this.receiptLogService = receiptLogService;
    }

    @Override
    public ReceiptResponseDto save(ReceiptRequestDto receiptRequestDto) throws Exception {

        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        ReceiptLogRequestDto receiptLogRequestDto = new  ReceiptLogRequestDto();
        Receipt receipt = new Receipt();
        List<Book> books = receiptRequestDto.getBooks();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            receiptRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setReceiptDate(LocalDate.now())
                    .setReceiptTime(LocalTime.now())
                    .setUser(user)
                    .setCreatedBy(user.getUsername())
                    .setReceiptStatus(ReceiptStatus.INIT_REGISTRATION);
        }

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

        receiptLogRequestDto
                .setCreatedBy(receiptResponseDto.getCreatedBy())
                .setCreatedDate(receiptResponseDto.getCreatedDate())
                .setReceiptStatus(ReceiptStatus.INIT_REGISTRATION)
                .setReceipt(receiptSaved)
                .setCreatedTime(receiptResponseDto.getCreatedTime());

        receiptLogService.save(receiptLogRequestDto);

        return receiptResponseDto;
    }

    @Override
    public ReceiptResponseDto update(ReceiptRequestDto receiptRequestDto) throws Exception {

        ReceiptResponseDto receiptResponseDto = new ReceiptResponseDto();
        Receipt receipt = new Receipt();
        List<Book> books = receiptResponseDto.getBooks();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        if( user != null )
        {
            receiptRequestDto
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setReceiptDate(LocalDate.now())
                    .setReceiptTime(LocalTime.now())
                    .setUser(user)
                    .setCreatedBy(user.getUsername())
                    .setReceiptStatus(ReceiptStatus.INIT_REGISTRATION);
        }

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

    @Override
    public List<ReceiptViewResponseDto> findAllReceiptsView() {

        List<ReceiptViewResponseDto> receiptViewResponseDtoList = new ArrayList<>();
        List<ReceiptView> receipts = receiptRepository.findAllReceiptsView();

        receipts.stream()
                .forEach(receipt ->
                {
                    ReceiptViewResponseDto receiptViewResponseDto = new ReceiptViewResponseDto();
                    BeanUtils.copyProperties(receipt, receiptViewResponseDto);
                    receiptViewResponseDtoList.add(receiptViewResponseDto);
                });

        return receiptViewResponseDtoList;
    }

    @Override
    public List<ReceiptViewResponseDto> findAllReceiptsViewByUsername(String username) {

        List<ReceiptViewResponseDto> receiptViewResponseDtoList = new ArrayList<>();

        if (username == null || username.trim().isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                username = user.getUsername();
            }
        }

        if (username != null && !username.trim().isEmpty()) {
            List<ReceiptView> findReceiptsByUsername = receiptRepository.findAllReceiptsByUsername(username);

            findReceiptsByUsername.forEach(receipt -> {
                ReceiptViewResponseDto receiptViewResponseDto = new ReceiptViewResponseDto();
                BeanUtils.copyProperties(receipt, receiptViewResponseDto);
                receiptViewResponseDtoList.add(receiptViewResponseDto);
            });
        }

        return receiptViewResponseDtoList;
    }

    @Override
    public void approveAction(ReceiptResponseDto receiptResponseDto) {

    }

        @Override
        public void rejectAction(ReceiptResponseDto receiptResponseDto) throws Exception {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            ReceiptLogRequestDto receiptLogRequestDto = new ReceiptLogRequestDto();
            Receipt findReceiptById = receiptRepository.findById(receiptResponseDto.getReceiptId()).orElse(null);

            if( findReceiptById == null ){
                throw new Exception("Receipt id not found");
            }

            findReceiptById
                            .setReceiptStatus(ReceiptStatus.REJECTED)
                            .setModifiedDate(LocalDate.now())
                            .setModifiedTime(LocalTime.now())
                            .setDescription(receiptResponseDto.getDescription())
                            .setModifiedBy(user.getUsername());

            Receipt receipt = receiptRepository.save(findReceiptById);
            ReceiptLogResponseDto findReceiptLogByReceipt = receiptLogService.findReceiptLogByReceipt(receipt.getReceiptId());

            receiptLogRequestDto
                    .setReceiptLogId(findReceiptLogByReceipt.getReceiptLogId())
                    .setCreatedDate(LocalDate.now())
                    .setCreatedTime(LocalTime.now())
                    .setCreatedBy(user.getUsername())
                    .setReceipt(receipt)
                    .setReceiptStatus(ReceiptStatus.REJECTED);

            receiptLogService.save(receiptLogRequestDto);
        }

    @Override
    public void returnAction(ReceiptResponseDto receiptResponseDto) {

    }
}