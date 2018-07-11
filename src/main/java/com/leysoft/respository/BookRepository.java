package com.leysoft.respository;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.leysoft.document.Book;

public interface BookRepository extends ElasticsearchCrudRepository<Book, String> {
	
	@Query(value = "{\"bool\": {\"must\": {\"term\" : {\"name\": \"?0\"}}}}")
	public Iterable<Book> findByNameCustom(String name);
	
	@Query(value = "{\"bool\": {\"must\": {\"term\": {\"author.name\": \"?0\"}}}}")
	public Iterable<Book> findByAuthorName(String name);
}
