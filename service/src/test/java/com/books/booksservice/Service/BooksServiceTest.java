package com.books.booksservice.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.books.booksservice.data.BookRepository;
import com.books.booksservice.data.model.Book;
import com.books.booksservice.service.BooksService;
@ExtendWith(MockitoExtension.class)
public class BooksServiceTest {

    @Mock
    private BookRepository bookRepo;

    @InjectMocks
    private BooksService booksService;

     

    private Book bookFixture = Book.builder().id(Long.valueOf(1))
    .title("title1").author("author1").build();



    @Test
    public void addBook_shouldReturn_SavedBook(){

        Book bookToAdd = Book.builder().title("title1").author("author1").build();
        
        when(bookRepo.save(bookToAdd)).thenReturn(bookFixture);

        assertEquals(bookFixture, booksService.saveBook(bookToAdd));

    }

    
}
