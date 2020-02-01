package com.epam.lab.repository.impl;

import com.epam.lab.dto.News;
import com.epam.lab.dto.Tag;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.repository.Joinable;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class TagRepositoryImpl implements EntityRepository<Tag>, Joinable<News, Tag> {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_TAG = "INSERT INTO tag (name) VALUES (?)";
    private static final String SQL_UPDATE_TAG = "UPDATE tag SET name = coalesce(?, name) WHERE id = ?";
    private static final String SQL_REMOVE_TAG = "DELETE FROM tag WHERE id = ?";
    private static final String SQL_INSERT_NEWS_TAG = "INSERT INTO news_tag (news_id, tag_id) VALUES (?, ?)";

    @Autowired
    public TagRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tag save(Tag tag) {
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
        jdbcTemplate.update(SQL_UPDATE_TAG, tag.getName(), tag.getId());
    }

    @Override
    public void remove(Tag tag) {
        jdbcTemplate.update(SQL_REMOVE_TAG, tag.getId());
    }

    @Override
    public List<Tag> query(EntitySpecification specification) {
        List<Tag> tags = jdbcTemplate.query(specification.specified(),
                (rs, rowNum) -> new Tag()
                        .setId(rs.getInt("id"))
                        .setName(rs.getString("name")));
        return tags;
    }

    @Override
    public void join(News news, Tag tag) {
        jdbcTemplate.update(SQL_INSERT_NEWS_TAG, news.getId(), tag.getId());
    }
}
