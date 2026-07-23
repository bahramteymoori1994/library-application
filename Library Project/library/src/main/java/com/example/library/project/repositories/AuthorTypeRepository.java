package com.example.library.project.repositories;

import com.example.library.project.model.entities.AuthorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorTypeRepository extends JpaRepository<AuthorType, Long> {
}
