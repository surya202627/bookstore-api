package com.bookstore.repository;

import com.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, 
                                        JpaSpecificationExecutor<Book> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByGenre(String genre);
    List<Book> findByPriceBetween(BigDecimal min, BigDecimal max);
    List<Book> findByPublicationDateAfter(LocalDate date);
    List<Book> findByAuthor_LastNameContainingIgnoreCase(String lastName);
}