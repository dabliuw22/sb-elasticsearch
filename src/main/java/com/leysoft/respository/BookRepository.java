package com.leysoft.respository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

import com.leysoft.document.Book;

public interface BookRepository extends ElasticsearchCrudRepository<Book, String> {
	
	@Query(value = "{\"bool\": {\"must\": [{\"match\": {\"author.name\": \"?0\"}}]}}")
	public List<Book> findByAuthorName(String authorName);
}
