package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_AUTHOR =
            "SELECT * FROM author WHERE id = ?";

    private long id;

    public AuthorIdSpecification(long id) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_AUTHOR);
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
    }
}
