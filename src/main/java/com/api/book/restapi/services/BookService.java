package com.api.book.restapi.services;

import java.util.List;

import com.api.book.restapi.dao.BookRepository;
import com.api.book.restapi.entities.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> list = new ArrayList<>();

    // static {
    // list.add(new Book(101, "A B C", "MR.X"));
    // list.add(new Book(102, "X Y Z", "MR.X"));
    // list.add(new Book(103, "P Q R", "MR.X"));
    // }

    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id) {
        Book book = null;
        try {
            // book = list.stream().filter(e -> e.getId() == id).findFirst().get();

            book = bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public Book addBook(Book book) {
        Book b = bookRepository.save(book);
        return b;
    }

    public void deleteBook(int id) {
        // Book book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        // list.remove(book);
        // return book;
        bookRepository.deleteById(id);

    }

    public void updateBook(Book book, int id) {
        book.setId(id);
        bookRepository.save(book);
    }
}
