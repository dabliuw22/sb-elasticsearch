package com.leysoft.service.imple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leysoft.document.Book;
import com.leysoft.respository.BookRepository;
import com.leysoft.service.inter.BookService;

@Service
public class BookServiceImp implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book findById(String id) {
		return bookRepository.findById(id).orElse(null);
	}

	@Override
	public Iterable<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findByAuthorName(String authorName) {
		return bookRepository.findByAuthorName(authorName);
	}

	@Override
	public boolean update(Book book) {
		return bookRepository.save(book) != null;
	}

	@Override
	public boolean delete(String id) {
		boolean deleted = false;
		Book book = findById(id);
		if(book != null) {
			bookRepository.delete(book);
			deleted = true;
		}
		return deleted;
	}
}
