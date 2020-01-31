package com.epam.lab.repository.impl;

import com.epam.lab.dto.Tag;
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
public class TagRepositoryImpl implements EntityRepository<Tag> {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_NEWS =
            "INSERT INTO tag (name) VALUES (?)";
    private static final String SQL_UPDATE_NEWS =
            "UPDATE tag SET name = ? WHERE id = ?";
    private static final String SQL_REMOVE_NEWS =
            "DELETE FROM tag WHERE id = ?";

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Tag tag) {
        jdbcTemplate.update(SQL_INSERT_NEWS, tag.getName());
    }

    public void update(Tag tag) {
        jdbcTemplate.update(SQL_UPDATE_NEWS, tag.getName(), tag.getId());
    }

    public void remove(Tag tag) {
        jdbcTemplate.update(SQL_REMOVE_NEWS, tag.getId());
    }

    public List<Tag> query(EntitySpecification specification) {
        List<Tag> tags = jdbcTemplate.query(specification.specified(),
                new RowMapper<Tag>() {
                    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Tag()
                                .setId(rs.getInt("id"))
                                .setName(rs.getString("name"));
                    }
                });
        return tags;
    }

}
