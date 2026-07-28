package com.bookstore.repository;

import com.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, 
                                          JpaSpecificationExecutor<Author> {
    Optional<Author> findByEmail(String email);
    List<Author> findByLastNameContainingIgnoreCase(String lastName);
    List<Author> findByNationality(String nationality);
}