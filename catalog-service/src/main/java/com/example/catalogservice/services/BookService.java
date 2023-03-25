package com.example.catalogservice.services;

import com.example.catalogservice.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BookService {
    Book create(Book book);
    Book getBook(Long id);
    Book updateBook(Long id,Book book);
    void deleteBook(Long id);
    Page<Book> getAllBooks(String title, String author, Double price, int page, int size, List<String> sortList, String sortOrder);

}
