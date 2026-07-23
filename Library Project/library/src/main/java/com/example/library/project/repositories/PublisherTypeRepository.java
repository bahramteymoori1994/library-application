package com.example.library.project.repositories;

import com.example.library.project.model.entities.PublisherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherTypeRepository extends JpaRepository<PublisherType, Long> {
}