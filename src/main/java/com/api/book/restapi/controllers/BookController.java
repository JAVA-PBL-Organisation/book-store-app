package com.api.book.restapi.controllers;

import java.util.List;

import com.api.book.restapi.entities.Book;
import com.api.book.restapi.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", "kaustubh");
        return "home";
    }

    @GetMapping("/books")
    public String getbooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        if (books.size() <= 0) {
            model.addAttribute("message", "No books found");
            return "error";
        }
        model.addAttribute("books", books);
        return "index";
    }

    @GetMapping("/book/{id}")
    public String getbook(Model model, @PathVariable("id") int id) {

        Book book = bookService.getBookById(id);
        if (book == null) {
            model.addAttribute("message", "No book found");
            return "error";
        }

        model.addAttribute(book);
        return "show";
    }

    @GetMapping("/books/new")
    public String newForm(Book book) {
        return "new";
    }

    @PostMapping("/book/add")
    public String addBook(Book book, Model model) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            model.addAttribute("book", b);
            return "show";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @DeleteMapping("/book/{id}")
    public String deletebBook(@PathVariable("id") int id, Model model) {
        try {
            this.bookService.deleteBook(id);
            model.addAttribute("books", bookService.getAllBooks());
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Book you trying to delete doesn't exist!");
            return "error";
        }
    }

    @GetMapping("/books/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "edit";
        }
        model.addAttribute("message", "Book you trying to edit doesn't exist!");
        return "error";
    }

    @PutMapping("/book/update/{id}")
    public String updateBook(@PathVariable("id") int id, Book book, Model model) {
        try {
            this.bookService.updateBook(book, id);
            model.addAttribute("books", bookService.getAllBooks());
            return "index";
        } catch (Exception e) {
            return "error";
        }
    }
}
