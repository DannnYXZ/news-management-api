package com.epam.lab.dto;

public class AuthorDTO {

    private long id;
    private String name;
    private String surname;

    public AuthorDTO() {
    }

    public AuthorDTO(String name) {
        this.name = name;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDTO authorDTO = (AuthorDTO) o;

        if (id != authorDTO.id) return false;
        if (name != null ? !name.equals(authorDTO.name) : authorDTO.name != null) return false;
        return surname != null ? surname.equals(authorDTO.surname) : authorDTO.surname == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
