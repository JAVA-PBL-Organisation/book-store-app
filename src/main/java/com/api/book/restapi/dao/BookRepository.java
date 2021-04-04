package com.api.book.restapi.dao;

import com.api.book.restapi.entities.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Book findById(int id);
}
