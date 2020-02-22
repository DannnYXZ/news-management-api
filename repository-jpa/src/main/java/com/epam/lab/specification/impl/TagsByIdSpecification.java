package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import java.sql.PreparedStatement;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class TagsByIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_TAG = "SELECT * FROM tag WHERE id = ?";
    private long id;

    public TagsByIdSpecification(long id) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_TAG);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
    }
}
