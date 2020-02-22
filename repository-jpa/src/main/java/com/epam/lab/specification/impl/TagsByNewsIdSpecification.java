package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class TagsByNewsIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_NEWS_TAGS = "SELECT * FROM tag INNER JOIN " +
        "news_tag ON news_tag.news_id = ? AND tag.id = news_tag.tag_id";
    private long id;

    public TagsByNewsIdSpecification(long id) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_NEWS_TAGS);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
    }
}
