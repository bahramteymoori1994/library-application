package com.example.library.project.repositories;

import com.example.library.project.model.entities.LibraryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryTypeRepository extends JpaRepository<LibraryType, Long> {
}
