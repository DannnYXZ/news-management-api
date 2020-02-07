package com.epam.lab.repository.impl;

import com.epam.lab.exception.EntityExistsException;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.exception.InsufficientEntityDataException;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.specification.EntitySpecification;
import com.epam.lab.specification.impl.AuthorsByNewsIdSpecification;
import com.epam.lab.specification.impl.TagsByNewsIdSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepository {
    @Autowired
    private EntityRepository<Tag> tagRepository;
    @Autowired
    private EntityRepository<Author> authorRepository;

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_NEWS = "INSERT INTO news " +
            "(title, short_text, full_text, creation_date, modification_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_NEWS = "UPDATE news SET " +
            "title = coalesce(?, title), " +
            "short_text = coalesce(?, short_text), " +
            "full_text = coalesce(?, full_text), " +
            "creation_date = coalesce(?, creation_date), " +
            "modification_date = coalesce(?, modification_date) WHERE id = ?";
    private static final String SQL_REMOVE_NEWS = "DELETE FROM news WHERE id = ?";
    private static final String SQL_COUNT_NEWS = "SELECT COUNT(*) FROM news";
    private static final String SQL_INSERT_NEWS_TAG = "INSERT INTO news_tag (news_id, tag_id) VALUES (?, ?)";
    private static final String SQL_REMOVE_NEWS_TAG = "DELETE FROM news_tag WHERE news_id = ? AND tag_id = ?";
    private static final String SQL_INSERT_NEWS_AUTHOR = "INSERT INTO news_author (news_id, author_id) VALUES (?, ?)";
    private static final String SQL_REMOVE_NEWS_AUTHOR = "DELETE FROM news_author WHERE news_id = ? AND author_id = ?";

    @Autowired
    public NewsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public News create(News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_INSERT_NEWS, new String[]{"id"});
                ps.setString(1, news.getTitle());
                ps.setString(2, news.getShortText());
                ps.setString(3, news.getFullText());
                ps.setDate(4, new java.sql.Date(news.getCreationDate().getTime()));
                ps.setDate(5, new java.sql.Date(news.getModificationDate().getTime()));
                return ps;
            }, keyHolder);
            news.setId(keyHolder.getKey().longValue());
        } catch (DataIntegrityViolationException e) {
            throw new InsufficientEntityDataException("Not enough data.");
        }
        List<Tag> tags = news.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                Tag identifiedTag = tagRepository.create(tag);
                linkTag(news, identifiedTag);
            }
        }
        Author author = news.getAuthor();
        if (author != null) {
            Author identifiedAuthor = authorRepository.create(author);
            linkAuthor(news, identifiedAuthor);
        }
        return news;
    }

    @Override
    public void update(News news) {
        if (jdbcTemplate.update(SQL_UPDATE_NEWS,
                news.getTitle(),
                news.getShortText(),
                news.getFullText(),
                news.getCreationDate(),
                news.getModificationDate(),
                news.getId()) == 0) {
            throw new EntityNotFoundException("No such news.");
        }
    }

    @Override
    public void delete(News news) {
        if (jdbcTemplate.update(SQL_REMOVE_NEWS, news.getId()) == 0) {
            throw new EntityNotFoundException("No such news.");
        }
    }

    @Override
    public long count() {
        return jdbcTemplate.query(SQL_COUNT_NEWS, resultSet -> {
            resultSet.next();
            return resultSet.getLong(1);
        });
    }

    @Override
    public List<News> query(EntitySpecification specification) {
        List<News> news = jdbcTemplate.query(specification.specified(),
                (rs, rowNum) -> new News()
                        .setId(rs.getInt("id"))
                        .setTitle(rs.getString("title"))
                        .setShortText(rs.getString("short_text"))
                        .setFullText(rs.getString("full_text"))
                        .setCreationDate(rs.getDate("creation_date"))
                        .setModificationDate(rs.getDate("modification_date")));
        for (News item : news) {
            item.setTags(tagRepository.query(new TagsByNewsIdSpecification(item.getId())));
            List<Author> authors = authorRepository.query(new AuthorsByNewsIdSpecification(item.getId()));
            item.setAuthor(!authors.isEmpty() ? authors.get(0) : null);
        }
        return news;
    }

    @Override
    public void linkAuthor(News news, Author author) {
        try {
            jdbcTemplate.update(SQL_INSERT_NEWS_AUTHOR, news.getId(), author.getId());
        } catch (DuplicateKeyException e) {
            throw new EntityExistsException("News can have only one author.");
        }
    }

    @Override
    public void unlinkAuthor(News news, Author author) {
        if (jdbcTemplate.update(SQL_REMOVE_NEWS_AUTHOR, news.getId(), author.getId()) == 0) {
            throw new EntityNotFoundException("News " + news.getId() + "have no author " + author.getId() + ".");
        }
    }

    @Override
    public void linkTag(News news, Tag tag) {
        try {
            jdbcTemplate.update(SQL_INSERT_NEWS_TAG, news.getId(), tag.getId());
        } catch (DuplicateKeyException e1) {
            throw new EntityExistsException("Tag " + tag.getId() + " already attached.");
        } catch (DataIntegrityViolationException e2) {
            throw new EntityNotFoundException("No tag with id " + tag.getId() + " found.");
        }
    }

    @Override
    public void unlinkTag(News news, Tag tag) {
        if (jdbcTemplate.update(SQL_REMOVE_NEWS_TAG, news.getId(), tag.getId()) == 0) {
            throw new EntityExistsException("No tag with id " + tag.getId() + " attached.");
        }
    }
}
