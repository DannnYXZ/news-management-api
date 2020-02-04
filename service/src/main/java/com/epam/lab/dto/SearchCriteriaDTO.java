package com.epam.lab.dto;

import java.util.List;

public class SearchCriteriaDTO {
    private AuthorDTO author;
    private List<TagDTO> tags;
    private SortCriteriaDTO sortCriteria;

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

    public SortCriteriaDTO getSortCriteria() {
        return sortCriteria;
    }

    public SearchCriteriaDTO setSortCriteria(SortCriteriaDTO sortCriteria) {
        this.sortCriteria = sortCriteria;
        return this;
    }
}
