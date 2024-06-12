package com.project.readers.readers_community.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

//represents a book genre
@Entity
@Table(name="book_categories")
public class BookCategory
{
    //auto generated id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //name of the category
    @Column(name = "name")
    private String name;

    //books that belong to this category
    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private List<Book> books;

    public BookCategory() {
    }

    public BookCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
