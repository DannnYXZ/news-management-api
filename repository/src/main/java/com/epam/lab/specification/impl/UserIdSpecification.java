package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_USER =
            "SELECT * FROM \"user\" WHERE id = ?";

    private long id;

    public UserIdSpecification(String name) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
    }
}
