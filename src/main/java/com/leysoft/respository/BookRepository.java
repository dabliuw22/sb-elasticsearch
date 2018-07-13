
package com.leysoft.respository;

import java.util.List;

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
            value = "{\"nested\": {\"path\": \"author\", \"query\": {\"bool\": {\"must\": [{\"match\": {\"author.name\": \"?0\"}}]}}}}")
    public List<Book> findByAuthorName(String name);
}
