package com.epam.lab.repository.impl;

import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements EntityRepository<Author> {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_AUTHOR = "INSERT INTO author (name, surname) VALUES (?, ?)";
    private static final String SQL_UPDATE_AUTHOR = "UPDATE author SET " +
        "name = coalesce(?, name), " +
        "surname = coalesce(?, surname) WHERE id = ?";
    private static final String SQL_REMOVE_AUTHOR = "DELETE FROM author WHERE id = ?";

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author create(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_AUTHOR, new String[]{"id"});
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            return ps;
        }, keyHolder);
        author.setId(keyHolder.getKey().longValue());
        return author;
    }

    @Override
    public void update(Author author) {
        if (isNotUpdated(
            jdbcTemplate.update(SQL_UPDATE_AUTHOR, author.getName(), author.getSurname(), author.getId()))) {
            throw new EntityNotFoundException("No such author.");
        }
    }

    @Override
    public void delete(Author author) {
        if (isNotUpdated(jdbcTemplate.update(SQL_REMOVE_AUTHOR, author.getId()))) {
            throw new EntityNotFoundException("No such author.");
        }
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
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
}
