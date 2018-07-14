
package com.leysoft.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leysoft.domain.Author;
import com.leysoft.domain.Editorial;

@Document(
        indexName = ".book",
        type = "books",
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
    private List<Author> authors;

    @Field(
            type = FieldType.Date,
            format = DateFormat.custom,
            pattern = "yyyy-MM-dd")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    private Date published;

    @Field(
            type = FieldType.Double)
    private Double price;

    private Editorial editorial;

    @Field(
            type = FieldType.Text,
            index = true)
    private String type;

    @Field(
            type = FieldType.Text)
    private String description;

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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
