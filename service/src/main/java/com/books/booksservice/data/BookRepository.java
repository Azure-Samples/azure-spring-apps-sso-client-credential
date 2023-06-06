package com.books.booksservice.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.booksservice.data.model.Book;

public interface  BookRepository extends JpaRepository<Book,Long>{
    
}
