package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "books",
       indexes = {
           @Index(name = "idx_book_title", columnList = "title"),
           @Index(name = "idx_book_isbn", columnList = "isbn"),
           @Index(name = "idx_book_genre", columnList = "genre")
       },
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_book_isbn", columnNames = "isbn")
       })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 200)
    private String title;
    
    @Column(unique = true, length = 13, nullable = false)
    private String isbn;
    
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    
    private Integer pages;
    
    @Column(length = 50)
    private String genre;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_book_author"))
    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Author author;
    
    // Audit fields for MySQL
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}