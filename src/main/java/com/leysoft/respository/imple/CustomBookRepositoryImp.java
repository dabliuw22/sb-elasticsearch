
package com.leysoft.respository.imple;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.SourceFilter;
import org.springframework.stereotype.Repository;

import com.leysoft.document.Book;
import com.leysoft.respository.inter.CustomBookRepository;

@Repository
public class CustomBookRepositoryImp implements CustomBookRepository {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public List<Book> findByName(String name) {
        SearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name).operator(Operator.AND)).build();
        return elasticsearchTemplate.queryForList(query, Book.class);
    }

    @Override
    public List<Book> findByAuthorName(String name) {
        QueryBuilder nestedQuery = nestedQuery("authors",
                boolQuery().must(matchQuery("authors.name", name)), ScoreMode.Avg);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(nestedQuery).build();
        return elasticsearchTemplate.queryForList(query, Book.class);
    }

    @Override
    public List<Book> findAll() {
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        return elasticsearchTemplate.queryForList(query, Book.class);
    }
    
    @Override
    public List<Book> findByPriceGteFilter(Double price) {
    	QueryBuilder filterQuery = rangeQuery("price").gte(price);
    	SearchQuery query = new NativeSearchQueryBuilder().withQuery(boolQuery().filter(filterQuery)).build();
    	return elasticsearchTemplate.queryForList(query, Book.class);
    }

    @Override
    public List<String> findByNameSourceName(String field, String name) {
    	SourceFilter sourceFilter = new FetchSourceFilterBuilder().withIncludes(field).build();
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery(field, name))
                .withSourceFilter(sourceFilter).build();
        return getResultBySource(field, query);
    }
    
    private List<String> getResultBySource(String field, SearchQuery query) {
    	return elasticsearchTemplate.query(query, response -> {
        	List<String> resultQuery = new ArrayList<>();
        	SearchHits hits = response.getHits();
        	hits.forEach(hit -> resultQuery.add((String) hit.getSource().get(field)));
        	return resultQuery;
        });
    }
}
