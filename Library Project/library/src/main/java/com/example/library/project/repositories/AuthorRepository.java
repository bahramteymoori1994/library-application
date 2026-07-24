package com.example.library.project.repositories;

import com.example.library.project.model.entities.Author;
import com.example.library.project.model.views.AuthorView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM test.author_view", nativeQuery = true)
    List<AuthorView> findAllAuthorsView();
}