package com.books.booksservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.booksservice.data.model.Book;
import com.books.booksservice.service.BooksService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController{
    private final BooksService booksService;

    @PreAuthorize("hasAuthority('APPROLE_Books.Read')")
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        return booksService.getBook(id);
    }

    @PreAuthorize("hasAuthority('APPROLE_Books.Write')")
    @PostMapping("/add")
    public Book addBook(@RequestBody Book book){
        return booksService.saveBook(book);
    }
}