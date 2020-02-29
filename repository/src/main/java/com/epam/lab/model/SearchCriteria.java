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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCriteria that = (SearchCriteria) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return sortCriteria == that.sortCriteria;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (sortCriteria != null ? sortCriteria.hashCode() : 0);
        return result;
    }
}
