package com.epam.lab.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class NewsDTO {
    private long id;
    private String title;
    private String shortText;
    private String fullText;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date creationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date modificationDate;
    private AuthorDTO author;
    private List<TagDTO> tags;

    public long getId() {
        return id;
    }

    public NewsDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public NewsDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getShortText() {
        return shortText;
    }

    public NewsDTO setShortText(String shortText) {
        this.shortText = shortText;
        return this;
    }

    public String getFullText() {
        return fullText;
    }

    public NewsDTO setFullText(String fullText) {
        this.fullText = fullText;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public NewsDTO setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public NewsDTO setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
        return this;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public NewsDTO setAuthor(AuthorDTO author) {
        this.author = author;
        return this;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public NewsDTO setTags(List<TagDTO> tags) {
        this.tags = tags;
        return this;
    }
}
