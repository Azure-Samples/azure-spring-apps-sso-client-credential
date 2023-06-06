package com.books.booksservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.books.booksservice.data.model.Book;
import com.books.booksservice.service.BooksService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BooksController{
    private final BooksService booksService;


    @PreAuthorize("hasRole('Task.Read')")
    @GetMapping("books/{id}")
    public Book getBook(@PathVariable Long id){
        return booksService.getBook(id);
    }

    @PreAuthorize("hasRole('Task.Write')")
    @PostMapping("/books/add")
    public Book addBook(@RequestBody Book book){
        return booksService.saveBook(book);
    }

}