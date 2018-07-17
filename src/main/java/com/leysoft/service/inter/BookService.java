
package com.leysoft.service.inter;

import java.util.Date;
import java.util.List;

import com.leysoft.document.Book;
import com.leysoft.dto.SourceResponse;

public interface BookService {

    public Book save(Book book);

    public Book findById(String id);

    public List<Book> findAll();

    public List<Book> findByName(String name);

    public List<Book> findByEditorialName(String name);

    public List<Book> findByAuthorName(String authorName);

    public List<Book> findByPublishedBetween(Date gte, Date lte);

    public List<Book> findByPriceAndDescription(Double price, String description);

    public List<Book> findByPriceGteFilter(Double price);

    public List<String> findByNameSourceName(String field, String name);

    public List<SourceResponse> findByNameSourceFields(String name, String... fields);

    public boolean update(Book book);

    public boolean delete(String id);
}
