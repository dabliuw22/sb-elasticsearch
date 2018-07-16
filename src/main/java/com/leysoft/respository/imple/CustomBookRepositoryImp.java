
package com.leysoft.respository.imple;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.nestedQuery;

import java.util.List;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilterBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.leysoft.document.Book;
import com.leysoft.respository.inter.CustomBookRepository;

@Repository
public class CustomBookRepositoryImp implements CustomBookRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomBookRepositoryImp.class);

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
        QueryBuilder queryBuilder = nestedQuery("authors",
                boolQuery().must(matchQuery("authors.name", name)), ScoreMode.Avg);
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        return elasticsearchTemplate.queryForList(query, Book.class);
    }

    @Override
    public List<Book> findAll() {
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).build();
        return elasticsearchTemplate.queryForList(query, Book.class);
    }

    @Override
    public String findByNameSourceName(String name) {
        SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery("name", name))
                .withSourceFilter(new FetchSourceFilterBuilder().withIncludes("name").build())
                .build();
        List<String> result = elasticsearchTemplate.queryForList(query, String.class);
        result.forEach(r -> LOGGER.info("ressult {}", r));
        return result.get(0);
    }
}
