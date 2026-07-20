package com.example.library.project.repositories;

import com.example.library.project.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByEnglishRoleTitleContainingIgnoreCaseOrFarsiRoleTitleContainingIgnoreCase(
            String englishTerm, String farsiTerm);

    List<Role> findByEnglishRoleTitleContainingIgnoreCase(String englishRoleTitle);

    List<Role> findByFarsiRoleTitleContainingIgnoreCase(String farsiRoleTitle);
}