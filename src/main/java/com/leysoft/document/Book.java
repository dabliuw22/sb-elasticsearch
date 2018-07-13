
package com.leysoft.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leysoft.domain.Author;

@Document(
        indexName = ".book",
        type = "book",
        replicas = 2,
        shards = 6)
public class Book {

    @Id
    private String id;

    @Field(
            type = FieldType.Text,
            index = true)
    private String name;

    @Field(
            type = FieldType.Nested)
    private Author author;

    @Field(
            type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    private Date published;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }
}
