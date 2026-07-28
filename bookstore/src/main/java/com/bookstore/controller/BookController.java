package com.bookstore.controller;

import com.bookstore.dto.BookDTO;
import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management", description = "APIs for managing books")
public class BookController {
    
    private final BookService bookService;
    
    @PostMapping
    @Operation(summary = "Create a new book")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book,
                                           @RequestParam Long authorId) {
        Book created = bookService.createBook(book, authorId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all books with pagination")
    public ResponseEntity<Page<Book>> getAllBooks(
            @PageableDefault(size = 10, sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }
    
    @GetMapping("/author/{authorId}")
    @Operation(summary = "Get books by author ID")
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }
    
    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get books by genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookService.getBooksByGenre(genre));
    }
    
    @GetMapping("/price-range")
    @Operation(summary = "Get books by price range")
    public ResponseEntity<List<Book>> getBooksByPriceRange(
            @RequestParam BigDecimal min, 
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(bookService.getBooksByPriceRange(min, max));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, 
                                           @Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}