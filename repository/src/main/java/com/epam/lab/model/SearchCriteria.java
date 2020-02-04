package com.epam.lab.model;

import java.util.List;

public class SearchCriteria {
    private Author author;
    private List<Tag> tags;
    private SortCriteria sortCriteria;

    public Author getAuthor() {
        return author;
    }

    public SearchCriteria setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public SearchCriteria setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public SortCriteria getSortCriteria() {
        return sortCriteria;
    }

    public SearchCriteria setSortCriteria(SortCriteria sortCriteria) {
        this.sortCriteria = sortCriteria;
        return this;
    }
}
