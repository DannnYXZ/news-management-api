package com.epam.lab.repository.impl;

import com.epam.lab.exception.EntityExistsException;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.specification.EntitySpecification;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static final String SQL_REMOVE_NEWS = "DELETE FROM News WHERE id = :id";
    private static final String SQL_COUNT_NEWS = "SELECT COUNT(*) FROM News";

    @Override
    public News create(News news) {
        entityManager.persist(news);
        return news;
    }

    @Override
    public void update(News news) {
        News storedNews = entityManager.find(News.class, news.getId());
        if (storedNews == null) {
            throw new javax.persistence.EntityNotFoundException("No such news.");
        }
        ObjectPatcher.patch(storedNews, news);
        news.setModificationDate(new Date());
    }

    @Override
    public void delete(News news) {
        if (isNotUpdated(entityManager.createQuery(SQL_REMOVE_NEWS)
            .setParameter("id", news.getId()).executeUpdate())) {
            throw new EntityNotFoundException("No such news.");
        }
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery(SQL_COUNT_NEWS);
        return (long) query.getSingleResult();
    }

    @Override
    public List<News> query(EntitySpecification<News> specification) {
        return entityManager.createQuery(specification.specified(entityManager)).getResultList();
    }

    @Override
    public void linkAuthor(News news, Author author) {
        News storedNews = entityManager.find(News.class, news.getId());
        if (storedNews.getAuthor() != null) {
            throw new EntityExistsException("News can have only one author.");
        }
        Author storedAuthor = entityManager.find(Author.class, author.getId());
        storedNews.setAuthor(storedAuthor);
    }

    @Override
    public void unlinkAuthor(News news, Author author) {
        News storedNews = entityManager.find(News.class, news.getId());
        if (storedNews.getAuthor().getId() != author.getId()) {
            throw new EntityExistsException("News have no such author.");
        }
        storedNews.setAuthor(null);
    }

    private void processTags(BiConsumer<List<Tag>, Tag> function, News news, Tag tag) {
        News storedNews = entityManager.find(News.class, news.getId());
        if (storedNews == null) {
            throw new EntityNotFoundException("No news with id " + news.getId());
        }
        Tag storedTag = entityManager.find(Tag.class, tag.getId());
        if (storedTag == null) {
            throw new EntityNotFoundException("No tag with id " + tag.getId());
        }
        try {
            function.accept(storedNews.getTags(), storedTag);
        } catch (DuplicateKeyException e) {
            throw new EntityExistsException("Tag " + tag.getId() + " already attached.");
        }
    }

    @Override
    public void linkTag(News news, Tag tag) {
        processTags(List::add, news, tag);
    }

    @Override
    public void unlinkTag(News news, Tag tag) {
        processTags(List::remove, news, tag);
    }
}
