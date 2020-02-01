package com.epam.lab.repository.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.dto.News;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.Joinable;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.util.List;

public class AuthorRepositoryImpl implements EntityRepository<Author>, Joinable<News, Author> {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_USER = "INSERT INTO author (name, surname) VALUES (?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE author SET " +
            "name = coalesce(?, name), " +
            "surname = coalesce(?, surname) WHERE id = ?";
    private static final String SQL_REMOVE_USER = "DELETE FROM author WHERE id = ?";
    private static final String SQL_INSERT_NEWS_AUTHOR = "INSERT INTO news_author (news_id, author_id) VALUES (?, ?)";

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_USER, new String[]{"id"});
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            return ps;
        }, keyHolder);
        author.setId(keyHolder.getKey().longValue());
        return author;
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(SQL_UPDATE_USER, author.getName(), author.getSurname(), author.getId());
    }

    @Override
    public void delete(Author author) {
        jdbcTemplate.update(SQL_REMOVE_USER, author.getId());
    }

    @Override
    public long count() {
        throw new NotImplementedException();
    }

    @Override
    public List<Author> query(EntitySpecification specification) {
        List<Author> authors = jdbcTemplate.query(specification.specified(),
                (rs, rowNum) -> new Author()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name"))
                        .setSurname(rs.getString("surname")));
        return authors;
    }

    @Override
    public void join(News news, Author author) {
        jdbcTemplate.update(SQL_INSERT_NEWS_AUTHOR, news.getId(), author.getId());
    }
}
