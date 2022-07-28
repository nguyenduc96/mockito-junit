package com.nguyenduc.mockito.repository;

import com.nguyenduc.mockito.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    public static List<Book> list = new ArrayList<>();

    public List<Book> getAll() {
        return list;
    }

    public Book getById(Long id) {
        if (id == null) {
            throw new RuntimeException("ID null");
        }
        for (var data: list) {
            if (data.getId().equals(id)) {
                return data;
            }
        }
        return null;
    }
}
