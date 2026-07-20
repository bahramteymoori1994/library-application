package com.example.library.project.repositories;

import com.example.library.project.model.entities.User;
import com.example.library.project.model.views.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM test.user_view",nativeQuery = true)
    List<UserView> findUsersView();
}