package com.example.library.project.repositories;

import com.example.library.project.model.entities.Receipt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {
}
