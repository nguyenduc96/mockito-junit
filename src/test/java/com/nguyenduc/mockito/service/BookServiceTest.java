package com.nguyenduc.mockito.service;

import com.nguyenduc.mockito.model.Book;
import com.nguyenduc.mockito.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.MockitoCore;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {
    @InjectMocks
    BookService bookService;

    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        List<Book> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add(new Book((long) i + 1, "Name Book " + i, "Author Book " + i));
        }
        when(bookRepository.getAll()).thenReturn((list));
        when(bookRepository.getById(1L)).thenReturn(new Book(1L));
    }


    @Test
    @DisplayName("getAll dữ liệu lấy ra nhiều hơn expected")
    void whenGetAll_DataGetGreaterThanExpected() {

        List list1 = bookService.getAll();

        assertThat(list1.size()).isGreaterThan(10);

        verify(bookRepository, Mockito.times(1)).getAll();
    }

      @Test
    @DisplayName("verify getAll 5 lần")
    void whenGetAll_DataGet5() {

        bookService.getAll();
        bookService.getAll();
        bookService.getAll();
        bookService.getAll();
        bookService.getAll();

        verify(bookRepository, Mockito.times(5)).getAll();
    }
   @Test
    @DisplayName("getAll 4 lần")
    void whenGetAll_DataGet4Time_Expected5() {

        bookService.getAll();
        bookService.getAll();
        bookService.getAll();
        bookService.getAll();

       verify(bookRepository, times(4)).getAll();

    }




    @Test
    @DisplayName("getAll dữ liệu lấy ra bằng hơn expected")
    void whenGetAll_DataGetEqualsThanExpected() {
        List list1 = bookService.getAll();

        assertEquals(list1.size(), 12);

        verify(bookRepository, times(1)).getAll();
    }

    @Test
    @DisplayName("getAll dữ liệu lấy ra bằng hơn expected")
    void whenGetAll_DataGetLessThanExpected() {
        List list1 = bookService.getAll();

        assertNotEquals(list1.size(), 14);

        verify(bookRepository).getAll();
    }

    @Test
    @DisplayName("Get by Id equals")
    void whenGetById() {
        Book book = new Book(1L, "", "");

        assertEquals(book, bookService.getById(1L));

        verify(bookRepository).getById(1L);
    }

    @Test
    @DisplayName("Get by Id Not equals")
    void whenGetById_NotEquals() {

        Book book = new Book(2L, "", "");

        assertNotEquals(book, bookService.getById(1L));

        verify(bookRepository).getById(1L);
    }

    @Test
    @DisplayName("Get by Id when id null")
    void whenGetById_IdNull() {
        BookRepository bookRepository1 = mock(BookRepository.class);
        doThrow(new RuntimeException("ID null")).when(bookRepository1).getById(anyLong());
        bookRepository1.getById(null);
    }
}
