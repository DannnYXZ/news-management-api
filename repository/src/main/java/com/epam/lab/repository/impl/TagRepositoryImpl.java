package com.epam.lab.repository.impl;


import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.PreparedStatement;
import java.util.List;

public class TagRepositoryImpl implements EntityRepository<Tag> {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_TAG = "INSERT INTO tag (name) VALUES (?)";
    private static final String SQL_UPDATE_TAG = "UPDATE tag SET name = coalesce(?, name) WHERE id = ?";
    private static final String SQL_REMOVE_TAG = "DELETE FROM tag WHERE id = ?";

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tag create(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT_TAG, new String[]{"id"});
            ps.setString(1, tag.getName());
            return ps;
        }, keyHolder);
        tag.setId(keyHolder.getKey().longValue());
        return tag;
    }

    @Override
    public void update(Tag tag) {
        if (isNotUpdated(jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(), tag.getId()))) {
            throw new EntityNotFoundException("No such tag.");
        }
    }

    @Override
    public void delete(Tag tag) {
        if (isNotUpdated(jdbcTemplate.update(SQL_REMOVE_TAG, tag.getId()))) {
            throw new EntityNotFoundException("No such tag.");
        }
    }

    @Override
    public long count() {
        throw new NotImplementedException();
    }

    @Override
    public List<Tag> query(EntitySpecification specification) {
        List<Tag> tags = jdbcTemplate.query(specification.specified(),
                (rs, rowNum) -> new Tag()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name")));
        return tags;
    }
}
