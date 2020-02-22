package com.epam.lab.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 200)
    private String title;
    @Column(name = "short_text", nullable = false, length = 500)
    private String shortText;
    @Column(name = "full_text", nullable = false, length = 2000)
    private String fullText;
    @Column(name = "creation_date", nullable = false)
    private Date creationDate;
    @Column(name = "modification_date", nullable = false)
    private Date modificationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(
        name = "news_author",
        joinColumns = @JoinColumn(name = "news_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Author author;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "news_tag",
        joinColumns = @JoinColumn(name = "news_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"news_id", "tag_id"})}
    )
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
