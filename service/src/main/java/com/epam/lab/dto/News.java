package com.epam.lab.dto;

import java.util.Date;
import java.util.List;

public class News {
    private long id;
    private String title;
    private String shortText;
    private String fullText;
    private Date creationDate;
    private Date modificationDate;
    private List<Tag> tags;

    public long getId() {
        return id;
    }

    public News setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getShortText() {
        return shortText;
    }

    public News setShortText(String shortText) {
        this.shortText = shortText;
        return this;
    }

    public String getFullText() {
        return fullText;
    }

    public News setFullText(String fullText) {
        this.fullText = fullText;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public News setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public News setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public News setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }
}
