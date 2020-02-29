package com.epam.lab.specification.impl;

import com.epam.lab.specification.EntitySpecification;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.PreparedStatement;

public class NewsByIdSpecification implements EntitySpecification {

    private static final String SQL_SELECT_NEWS = "SELECT * FROM news WHERE id = ?";
    private long id;

    public NewsByIdSpecification(long id) {
        this.id = id;
    }

    public PreparedStatementCreator specified() {
        return connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_NEWS);
            preparedStatement.setLong(1, id);
            return preparedStatement;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsByIdSpecification that = (NewsByIdSpecification) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
