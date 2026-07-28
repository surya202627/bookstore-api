package com.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String nationality;
    private String biography;
    private List<BookDTO> books;
}