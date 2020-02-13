package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class AuthorsByNewsIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_NEWS_AUTHOR = "SELECT * FROM author INNER JOIN " +
        "news_author ON news_author.news_id = ? AND author.id = news_author.author_id";
    private long id;

    public AuthorsByNewsIdSpecification(long id) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_NEWS_AUTHOR);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
    }
}
