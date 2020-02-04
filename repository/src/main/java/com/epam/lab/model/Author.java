package com.epam.lab.model;

public class Author implements Comparable<Author> {
    private long id;
    private String name;
    private String surname;

    public long getId() {
        return id;
    }

    public Author setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Author setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Author setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public int compareTo(Author author) {
        if (author == null) return -1;
        if (author.name == null) return -1;
        if (name == null) return 1;
        int cmp = name.compareTo(author.name);
        if (cmp != 0) return cmp;
        if (author.surname == null) return -1;
        if (surname == null) return 1;
        return surname.compareTo(author.name);
    }
}
