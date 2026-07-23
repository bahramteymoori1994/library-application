package com.example.library.project.repositories;

import com.example.library.project.model.entities.Publisher;
import com.example.library.project.model.views.PublisherView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Query(value = "SELECT * FROM test.publisher_view", nativeQuery = true)
    List<PublisherView> findAllPublishersView();
}