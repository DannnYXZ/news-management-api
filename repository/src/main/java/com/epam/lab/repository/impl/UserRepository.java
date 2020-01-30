package com.epam.lab.repository.impl;

import com.epam.lab.dto.User;
import com.epam.lab.repository.EntityRepository;
import com.epam.lab.specification.EntitySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserRepository implements EntityRepository<User> {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_USER =
            "INSERT INTO \"user\" (name, surname, login, password) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE \"user\" SET name = ?, surname = ?, login = ?, password = ? WHERE id = ?";
    private static final String SQL_REMOVE_USER =
            "DELETE FROM \"user\" WHERE id = ?";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword());
    }

    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), user.getId());
    }

    public void remove(User user) {
        jdbcTemplate.update(SQL_REMOVE_USER, user.getId());
    }

    public List<User> query(EntitySpecification specification) {
        List<User> users = jdbcTemplate.query(specification.specified(),
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User()
                                .setId(rs.getInt("id"))
                                .setName(rs.getString("name"))
                                .setSurname(rs.getString("surname"))
                                .setLogin(rs.getString("login"))
                                .setPassword(rs.getString("password"));
                    }
                });
        return users;
    }

}
