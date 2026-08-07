package com.example.library.project.repositories;

import com.example.library.project.model.entities.Translator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslatorRepository extends JpaRepository<Translator,Long> {
}
