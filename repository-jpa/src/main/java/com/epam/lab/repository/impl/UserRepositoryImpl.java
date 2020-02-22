package com.epam.lab.repository.impl;


import com.epam.lab.model.User;
import com.epam.lab.repository.UserRepository;
import com.epam.lab.specification.EntitySpecification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_INSERT_USER =
        "INSERT INTO \"user\" (name, surname, login, password) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER =
        "UPDATE \"user\" SET " +
            "name = coalesce(?, name), " +
            "surname = coalesce(?, surname), " +
            "login = coalesce(?, login), " +
            "password = coalesce(?, password) WHERE id = ?";
    private static final String SQL_REMOVE_USER = "DELETE FROM \"user\" WHERE id = ?";

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword());
        return user;
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword(),
            user.getId());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update(SQL_REMOVE_USER, user.getId());
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> query(EntitySpecification specification) {
        List<User> users = jdbcTemplate.query(specification.specified(),
            (rs, rowNum) -> new User()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setLogin(rs.getString("login"))
                .setPassword(rs.getString("password")));
        return users;
    }

}
