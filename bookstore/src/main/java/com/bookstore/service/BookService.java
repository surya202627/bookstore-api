package com.bookstore.service;

import com.bookstore.entity.Book;
import com.bookstore.entity.Author;
import com.bookstore.exception.ResourceNotFoundException;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    
    @Transactional
    public Book createBook(Book book, Long authorId) {
        Author author = authorRepository.findById(authorId)
            .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        book.setAuthor(author);
        return bookRepository.save(book);
    }
    
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }
    
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
    
    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
    
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }
    
    public List<Book> getBooksByPriceRange(BigDecimal min, BigDecimal max) {
        return bookRepository.findByPriceBetween(min, max);
    }
    
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublicationDate(bookDetails.getPublicationDate());
        book.setPrice(bookDetails.getPrice());
        book.setPages(bookDetails.getPages());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());
        return bookRepository.save(book);
    }
    
    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }
    
    public Page<Book> searchBooks(Specification<Book> spec, Pageable pageable) {
        return bookRepository.findAll(spec, pageable);
    }
}