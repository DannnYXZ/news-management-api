package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class UsersByIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_USER = "SELECT * FROM \"user\" WHERE id = ?";
    private long id;

    public UsersByIdSpecification(String name) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
    }
}
