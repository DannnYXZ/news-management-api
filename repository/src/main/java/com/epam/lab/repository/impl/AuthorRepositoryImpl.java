package com.epam.lab.repository.impl;

import com.epam.lab.dto.Author;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AuthorRepositoryImpl implements EntityRepository<Author> {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_USER =
            "INSERT INTO author (name, surname) VALUES (?, ?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE author SET name = ?, surname = ? WHERE id = ?";
    private static final String SQL_REMOVE_USER =
            "DELETE FROM author WHERE id = ?";

    @Autowired
    public AuthorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Author author) {
        jdbcTemplate.update(SQL_INSERT_USER, author.getName(), author.getSurname());
    }

    public void update(Author author) {
        jdbcTemplate.update(SQL_UPDATE_USER, author.getName(), author.getSurname(), author.getId());
    }

    public void remove(Author author) {
        jdbcTemplate.update(SQL_REMOVE_USER, author.getId());
    }

    public List<Author> query(EntitySpecification specification) {
        List<Author> authors = jdbcTemplate.query(specification.specified(),
                new RowMapper<Author>() {
                    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Author()
                                .setId(rs.getInt("id"))
                                .setName(rs.getString("name"))
                                .setSurname(rs.getString("surname"));
                    }
                });
        return authors;
    }

}
