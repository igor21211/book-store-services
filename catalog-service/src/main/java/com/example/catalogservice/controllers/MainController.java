package com.example.catalogservice.controllers;

import com.example.catalogservice.model.Book;
import com.example.catalogservice.services.BookService;
import com.sun.source.tree.LambdaExpressionTree;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainController {
    private BookService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book){
        return service.create(book);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("id") Long id){
        return service.getBook(id);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Page<Book> getAllBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) Double price,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "") List<String> sortList,
                                  @RequestParam(defaultValue = "DESC") String sort
                                  ){
        return service.getAllBooks(title,author,price,page,size,sortList,sort);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBookById(@PathVariable("id") Long id, @RequestBody Book book){
        return service.updateBook(id,book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookById(@PathVariable("id") Long id){
        service.deleteBook(id);
    }
}
