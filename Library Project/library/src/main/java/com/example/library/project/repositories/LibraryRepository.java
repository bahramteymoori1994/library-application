package com.example.library.project.repositories;

import com.example.library.project.model.entities.Library;
import com.example.library.project.model.views.LibraryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Query(value = "SELECT * FROM test.library_view", nativeQuery = true)
    List<LibraryView> findAllLibraries();
}