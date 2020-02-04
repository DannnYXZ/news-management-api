package com.epam.lab.dto;

public class AuthorDTO {
    private long id;
    private String name;
    private String surname;

    public long getId() {
        return id;
    }

    public AuthorDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AuthorDTO setSurname(String surname) {
        this.surname = surname;
        return this;
    }
}
