package com.example.library.project.repositories;

import com.example.library.project.model.entities.Book;
import com.example.library.project.model.views.BookView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM test.book_view", nativeQuery = true)
    List<BookView> findAllBooksView();
}