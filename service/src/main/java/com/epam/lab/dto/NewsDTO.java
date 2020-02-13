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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDTO newsDTO = (NewsDTO) o;

        if (id != newsDTO.id) return false;
        if (title != null ? !title.equals(newsDTO.title) : newsDTO.title != null) return false;
        if (shortText != null ? !shortText.equals(newsDTO.shortText) : newsDTO.shortText != null) return false;
        if (fullText != null ? !fullText.equals(newsDTO.fullText) : newsDTO.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(newsDTO.creationDate) : newsDTO.creationDate != null)
            return false;
        if (modificationDate != null ? !modificationDate.equals(newsDTO.modificationDate)
            : newsDTO.modificationDate != null)
            return false;
        if (author != null ? !author.equals(newsDTO.author) : newsDTO.author != null) return false;
        return tags != null ? tags.equals(newsDTO.tags) : newsDTO.tags == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
