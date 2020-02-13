package com.epam.lab.dto;

import java.util.List;

public class SearchCriteriaDTO {
    private AuthorDTO author;
    private List<TagDTO> tags;
    private SortCriteriaDTO sort;

    public AuthorDTO getAuthor() {
        return author;
    }

    public SearchCriteriaDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public SearchCriteriaDTO setTags(List<TagDTO> tags) {
        this.tags = tags;
        return this;
    }

    public SortCriteriaDTO getSort() {
        return sort;
    }

    public SearchCriteriaDTO setSort(SortCriteriaDTO sort) {
        this.sort = sort;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCriteriaDTO that = (SearchCriteriaDTO) o;

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
