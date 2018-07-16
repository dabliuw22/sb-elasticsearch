
package com.leysoft.respository.inter;

import java.util.List;

import com.leysoft.document.Book;
import com.leysoft.dto.SourceResponse;

public interface CustomBookRepository {

    public List<Book> findByName(String name);
    
    public List<Book> findByEditorialName(String name);

    public List<Book> findByAuthorName(String name);

    public List<Book> findAll();
    
    public List<Book> findByPriceGteFilter(Double price);

    public List<String> findByNameSourceName(String field, String name);
    
    public List<SourceResponse> findByNameSourceFields(String name, String... fields);
}
