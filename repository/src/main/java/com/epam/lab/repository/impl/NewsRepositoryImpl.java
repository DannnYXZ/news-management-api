package com.epam.lab.repository.impl;

import com.epam.lab.dto.News;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class NewsRepositoryImpl implements EntityRepository<News> {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_NEWS =
            "INSERT INTO news (title, short_text, full_text, creation_date, modification_date) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_NEWS =
            "UPDATE news SET title = ?, short_text = ?, full_text = ?, creation_date = ?, modification_date = ? WHERE id = ?";
    private static final String SQL_REMOVE_NEWS =
            "DELETE FROM news WHERE id = ?";

    @Autowired
    public NewsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(News news) {
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
    }

    public void update(News news) {
        jdbcTemplate.update(SQL_UPDATE_NEWS,
                news.getTitle(),
                news.getShortText(),
                news.getFullText(),
                news.getCreationDate(),
                news.getModificationDate(),
                news.getId());
    }

    public void remove(News news) {
        jdbcTemplate.update(SQL_REMOVE_NEWS, news.getId());
    }

    public List<News> query(EntitySpecification specification) {
        List<News> news = jdbcTemplate.query(specification.specified(),
                new RowMapper<News>() {
                    public News mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new News()
                                .setId(rs.getInt("id"))
                                .setTitle(rs.getString("title"))
                                .setShortText(rs.getString("short_text"))
                                .setFullText(rs.getString("full_text"))
                                .setCreationDate(rs.getDate("creation_date"))
                                .setModificationDate(rs.getDate("modification_date"));
                    }
                });
        return news;
    }

}
