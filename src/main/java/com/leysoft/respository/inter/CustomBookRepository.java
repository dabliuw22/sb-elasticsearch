
package com.leysoft.respository.inter;

import java.util.List;

import com.leysoft.document.Book;

public interface CustomBookRepository {

    public List<Book> findByName(String name);

    public List<Book> findByAuthorName(String name);

    public List<Book> findAll();

    public String findByNameSourceName(String name);
}
