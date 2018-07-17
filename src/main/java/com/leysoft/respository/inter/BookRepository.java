
package com.leysoft.respository.inter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.leysoft.document.Book;

public interface BookRepository extends ElasticsearchCrudRepository<Book, String> {

    @Query(
            value = "{\"match\": {\"name\": \"?0\"}}")
    public List<Book> findByNameCustom(String name);

    @Query(
            value = "{\"match_all\": {}}")
    public List<Book> findAllCustom();

    @Query(
            value = "{\"match\": {\"editorial.name\": \"?0\"}}")
    public List<Book> findByEditorialName(String name);

    @Query(
            value = "{\"nested\": {\"path\": \"authors\", \"query\": {\"bool\": "
                    + "{\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}}}")
    public List<Book> findByAuthorName(String name);

    @Query(
            value = "{\"nested\": {\"path\": \"authors\", \"query\": {\"bool\": "
                    + "{\"must\": [{\"match\": {\"authors.name\": \"?0\"}}]}}}}")
    public Page<Book> findByAuthorName(String name, Pageable pageable);
    
    @Query(
            value = "{\"range\": {\"published\": {\"gte\": \"?0\",\"lte\": \"?1\"}}}")
    public List<Book> findByPublishedBetween(String gte, String lte);

    @Query(
            value = "{\"bool\": {\"must\": [{\"term\": {\"price\": {\"value\": ?0}}},"
                    + "{\"match_phrase\": {\"description\": \"?1\"}}]}}")
    public List<Book> findByPriceAndDescription(Double price, String description);
}
