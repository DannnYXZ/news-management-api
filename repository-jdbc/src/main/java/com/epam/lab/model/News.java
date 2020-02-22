package com.epam.lab.model;

import java.util.Date;
import java.util.List;

public class News {

    private long id;
    private String title;
    private String shortText;
    private String fullText;
    private Date creationDate;
    private Date modificationDate;
    private Author author;
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

    public Author getAuthor() {
        return author;
    }

    public News setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public News setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        if (id != news.id) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (shortText != null ? !shortText.equals(news.shortText) : news.shortText != null) return false;
        if (fullText != null ? !fullText.equals(news.fullText) : news.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        if (modificationDate != null ? !modificationDate.equals(news.modificationDate) : news.modificationDate != null)
            return false;
        if (author != null ? !author.equals(news.author) : news.author != null) return false;
        return tags != null ? tags.equals(news.tags) : news.tags == null;
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
