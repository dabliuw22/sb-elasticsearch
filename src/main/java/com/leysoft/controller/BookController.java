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
import com.leysoft.service.inter.BookService;
import com.leysoft.util.Util;

@RestController
@RequestMapping(value = {"/book"})
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping(value = {"/{id}"})
	public ResponseEntity<Book> get(@PathVariable(name = "id") String id) {
		Book book = bookService.findById(id);
		return ResponseEntity.ok(book);
	}
	
	@GetMapping(value = {""})
	public ResponseEntity<List<Book>> all() {
		Iterable<Book> books = bookService.findAll();
		return ResponseEntity.ok(Util.iterableToList(books));
	}
	
	@PostMapping(value = {"/add"})
	public ResponseEntity<Book> add(@RequestBody Book book) {
		Book newBook = bookService.save(book);
		return ResponseEntity.ok(newBook);
	}
}
