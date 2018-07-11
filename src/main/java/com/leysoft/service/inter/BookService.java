package com.leysoft.service.inter;

import java.util.List;

import com.leysoft.document.Book;

public interface BookService {
	
	public Book save(Book book);
	
	public Book findById(String id);
	
	public List<Book> findAll();
	
	public List<Book> findByName(String name);
	
	public List<Book> findByAuthorName(String authorName);
	
	public boolean update(Book book);
	
	public boolean delete(String id);
}
