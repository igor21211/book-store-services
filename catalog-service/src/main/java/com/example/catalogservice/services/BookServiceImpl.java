package com.example.catalogservice.services;

import com.example.catalogservice.model.Book;
import com.example.catalogservice.repository.BookRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Book getBook(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("no found book with this id"));
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return repository.findById(id).map(
                book1 -> {
                    book1.setTitle(book.getTitle());
                    book1.setAuthor(book.getAuthor());
                    book1.setPrice(book.getPrice());
                    book1.setQuantity(book.getQuantity());
                    return repository.save(book1);
                }
        ).orElseThrow(() -> new RuntimeException("no found book with this id"));
    }

    @Override
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Book> getAllBooks(String title, String author, Double price, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(createSortOrder(sortList,sortOrder)));
        return repository.findAll(getBookSpecification(title,author,price),pageable);
    }

    private Specification<Book> getBookSpecification(final String title, final String author, final Double price) {
        return ((root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.and();
            if (title != null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("title"), "%" + title + "%"));
            if (author != null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("author"), "%" + author + "%"));
            if(price != null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("price"),"%"+price+"%"));
            return predicate;
        });

    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }


}
