package org.example.controller;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController{
    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService=bookService;
    }

    @GetMapping
    public List<Book>list(){
        return bookService.listAll();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id){
        return bookService.getById(id);
    }

    @PostMapping
    public Book add(@RequestBody Book book){
        bookService.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id,@RequestBody Book book){
        book.setId(id);
        bookService.update(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return bookService.delete(id);
    }

}
