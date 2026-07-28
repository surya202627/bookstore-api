package com.bookstore.controller;

import com.bookstore.dto.AuthorDTO;
import com.bookstore.entity.Author;
import com.bookstore.service.AuthorService;
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
import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Author Management", description = "APIs for managing authors")
public class AuthorController {
    
    private final AuthorService authorService;
    
    @PostMapping
    @Operation(summary = "Create a new author")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) {
        Author created = authorService.createAuthor(author);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get author by ID")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
    
    @GetMapping
    @Operation(summary = "Get all authors with pagination")
    public ResponseEntity<Page<Author>> getAllAuthors(
            @PageableDefault(size = 10, sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(authorService.getAllAuthors(pageable));
    }
    
    @GetMapping("/search")
    @Operation(summary = "Search authors by last name")
    public ResponseEntity<List<Author>> searchByLastName(@RequestParam String lastName) {
        return ResponseEntity.ok(authorService.getAuthorsByLastName(lastName));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing author")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, 
                                                @Valid @RequestBody Author author) {
        return ResponseEntity.ok(authorService.updateAuthor(id, author));
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an author")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}