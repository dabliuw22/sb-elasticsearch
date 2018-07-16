
package com.leysoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leysoft.document.Book;
import com.leysoft.dto.DateBetweenRequest;
import com.leysoft.dto.PriceAndDescriptionRequest;
import com.leysoft.service.inter.BookService;

@RestController
@RequestMapping(
        value = {
            "/book"
        })
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(
            value = {
                "/{id}"
            })
    public ResponseEntity<Book> get(@PathVariable(
            name = "id") String id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @GetMapping(
            value = {
                "/name/{name}"
            })
    public ResponseEntity<List<Book>> allByName(@PathVariable(
            name = "name") String name) {
        return ResponseEntity.ok(bookService.findByName(name));
    }

    @GetMapping(
            value = {
                "/author/{name}"
            })
    public ResponseEntity<List<Book>> allByAuthor(@PathVariable(
            name = "name") String name) {
        return ResponseEntity.ok(bookService.findByAuthorName(name));
    }

    @PostMapping(
            value = {
                "/between"
            })
    public ResponseEntity<List<Book>> allBetween(@RequestBody DateBetweenRequest request) {
        return ResponseEntity
                .ok(bookService.findByPublishedBetween(request.getGte(), request.getLte()));
    }

    @PostMapping(
            value = "/price/description")
    public ResponseEntity<List<Book>>
            allByPriceAndDescription(@RequestBody PriceAndDescriptionRequest request) {
        return ResponseEntity.ok(bookService.findByPriceAndDescription(request.getPrice(),
                request.getDescription()));
    }

    @GetMapping(
            value = {
                ""
            })
    public ResponseEntity<List<Book>> all() {
        List<Book> books = bookService.findAll();
        return ResponseEntity.ok(books);
    }

    @PostMapping(
            value = {
                "/add"
            })
    public ResponseEntity<Book> add(@RequestBody Book book) {
        Book newBook = bookService.save(book);
        return ResponseEntity.ok(newBook);
    }

    @GetMapping(
            value = {
                "/custom/name/{name}"
            })
    public ResponseEntity<String> getNameByName(@PathVariable(
            name = "name") String name) {
        return ResponseEntity.ok(bookService.findByNameSourceName(name));
    }
}
