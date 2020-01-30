package com.epam.lab.dto;

public class Tag {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public Tag setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }
}
