package com.epam.lab.repository.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class NewsRepositoryImpl implements EntityRepository<News> {
    @Autowired
    private EntityRepository<Tag> tagRepository;
    @Autowired
    private EntityRepository<Author> authorRepository;

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_NEWS = "INSERT INTO news " +
            "(title, short_text, full_text, creation_date, modification_date) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_NEWS = "UPDATE news SET " +
            "title = coalesce(?, title), " +
            "short_text = coalesce(?, short_text), " +
            "full_text = coalesce(?, full_text), " +
            "creation_date = coalesce(?, creation_date), " +
            "modification_date = coalesce(?, modification_date) WHERE id = ?";
    private static final String SQL_REMOVE_NEWS = "DELETE FROM news WHERE id = ?";
    private static final String SQL_COUNT_NEWS = "SELECT COUNT(*) FROM news";
    private static final String SQL_INSERT_NEWS_TAG = "INSERT INTO news_tag (news_id, tag_id) VALUES (?, ?)";
    private static final String SQL_INSERT_NEWS_AUTHOR = "INSERT INTO news_author (news_id, author_id) VALUES (?, ?)";

    @Autowired
    public NewsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public News create(News news) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
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
        List<Tag> tags = news.getTags();
        if (tags != null) {
            for (Tag tag : tags) {
                Tag identifiedTag = tagRepository.create(tag);
                jdbcTemplate.update(SQL_INSERT_NEWS_TAG, news.getId(), identifiedTag.getId());
            }
        }
        Author author = news.getAuthor();
        if (author != null) {
            Author identifiedAuthor = authorRepository.create(author);
            jdbcTemplate.update(SQL_INSERT_NEWS_AUTHOR, news.getId(), identifiedAuthor.getId());
        }
        return news;
    }

    @Override
    public void update(News news) {
        jdbcTemplate.update(SQL_UPDATE_NEWS,
                news.getTitle(),
                news.getShortText(),
                news.getFullText(),
                news.getCreationDate(),
                news.getModificationDate(),
                news.getId());
    }

    @Override
    public void delete(News news) {
        jdbcTemplate.update(SQL_REMOVE_NEWS, news.getId());
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
        return news;
    }

}
