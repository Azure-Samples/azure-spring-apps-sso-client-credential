package com.books.booksservice.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import com.books.booksservice.data.BookRepository;
import com.books.booksservice.data.model.Book;

@Service
@RequiredArgsConstructor
public class BooksService {
    private final BookRepository bookRepo;

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public Book getBook(Long bookId) {
        return bookRepo.findById(bookId).orElse(null);
    }

}
