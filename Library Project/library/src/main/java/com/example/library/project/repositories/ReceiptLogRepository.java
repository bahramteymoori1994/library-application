package com.example.library.project.repositories;

import com.example.library.project.model.entities.ReceiptLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptLogRepository extends JpaRepository<ReceiptLog, Long> {
}
