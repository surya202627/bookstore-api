package com.bookstore.service;

import com.bookstore.entity.Author;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    
    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }
    
    public Page<Author> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
    
    public List<Author> getAuthorsByLastName(String lastName) {
        return authorRepository.findByLastNameContainingIgnoreCase(lastName);
    }
    
    @Transactional
    public Author updateAuthor(Long id, Author authorDetails) {
        Author author = getAuthorById(id);
        author.setFirstName(authorDetails.getFirstName());
        author.setLastName(authorDetails.getLastName());
        author.setEmail(authorDetails.getEmail());
        author.setBirthDate(authorDetails.getBirthDate());
        author.setNationality(authorDetails.getNationality());
        author.setBiography(authorDetails.getBiography());
        return authorRepository.save(author);
    }
    
    @Transactional
    public void deleteAuthor(Long id) {
        Author author = getAuthorById(id);
        authorRepository.delete(author);
    }
    
    public Page<Author> searchAuthors(Specification<Author> spec, Pageable pageable) {
        return authorRepository.findAll(spec, pageable);
    }
}