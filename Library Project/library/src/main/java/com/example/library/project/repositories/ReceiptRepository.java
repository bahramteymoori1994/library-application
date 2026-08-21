package com.example.library.project.repositories;

import com.example.library.project.model.entities.Receipt;
import com.example.library.project.model.views.ReceiptView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    @Query(value = "SELECT * FROM receipt_view", nativeQuery = true)
    List<ReceiptView> findAllReceiptsView();

    @Query(value = "SELECT * FROM receipt_view WHERE created_by = :username", nativeQuery = true)
    List<ReceiptView> findAllReceiptsByUsername(@Param("username") String username);
}