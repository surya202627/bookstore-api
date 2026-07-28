package com.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publicationDate;
    private BigDecimal price;
    private Integer pages;
    private String genre;
    private String description;
    private Long authorId;
    private String authorName;
}