package com.epam.lab.model;

import java.util.List;

public class SearchCriteria {

    private Author author;
    private List<Tag> tags;
    private SortCriteria sort;

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

    public SortCriteria getSort() {
        return sort;
    }

    public SearchCriteria setSort(SortCriteria sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCriteria that = (SearchCriteria) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return sort == that.sort;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }
}
