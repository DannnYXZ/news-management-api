package com.epam.lab.dto;

public class TagDTO {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public TagDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TagDTO setName(String name) {
        this.name = name;
        return this;
    }
}
