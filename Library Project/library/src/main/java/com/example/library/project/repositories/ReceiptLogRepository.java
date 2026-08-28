package com.example.library.project.repositories;

import com.example.library.project.model.entities.ReceiptLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptLogRepository extends JpaRepository<ReceiptLog, Long> {

    @Query(value = "SELECT * FROM receipt_log WHERE receipt_id = :receiptId", nativeQuery = true)
    Optional<ReceiptLog> findReceiptLogByReceipt(@Param("receiptId") Long receiptId);
}