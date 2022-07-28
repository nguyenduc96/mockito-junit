package com.nguyenduc.mockito.service;

import com.nguyenduc.mockito.model.Book;
import com.nguyenduc.mockito.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    public Book getById(Long id) {
        return bookRepository.getById(id);
    }
}
