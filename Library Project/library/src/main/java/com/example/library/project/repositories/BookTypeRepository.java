package com.example.library.project.repositories;

import com.example.library.project.model.entities.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {
}
