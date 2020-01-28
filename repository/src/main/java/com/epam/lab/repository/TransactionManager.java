package com.epam.lab.repository;

import com.epam.lab.configuration.DataSource;
import com.epam.lab.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public final class TransactionManager {
    private static Logger logger = LogManager.getLogger();
    private Connection proxyConnection;

    public void begin(GenericDAO dao, GenericDAO... daos) throws DAOException {
        try {
            proxyConnection = DataSource.getConnection();
            proxyConnection.setAutoCommit(false);
            injectConnection(dao, proxyConnection);
            for (GenericDAO d : daos) {
                injectConnection(d, proxyConnection);
            }
        } catch (SQLException e) {
            throw new DAOException("Failed to create transaction.", e);
        }
    }

    public void commit() throws DAOException {
        try {
            proxyConnection.commit();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void rollback() {
        try {
            proxyConnection.rollback();
        } catch (SQLException e) {
            logger.error("Failed to rollback transaction.", e);
        }
    }

    public void end() {
        try {
            proxyConnection.setAutoCommit(true);
            proxyConnection.close();
        } catch (SQLException e) {
            logger.error("Failed to close transaction.", e);
        }
    }

    private static void injectConnection(GenericDAO dao, Connection connection) throws DAOException {
        try {
            Field targetField = GenericDAO.class.getDeclaredField("connection");
            targetField.setAccessible(true);
            targetField.set(dao, connection);
        } catch (IllegalAccessException e) {
            throw new DAOException("Failed to inject connection in transactional DAO.", e);
        } catch (NoSuchFieldException e) {
            throw new DAOException("Failed to inject connection in transactional DAO.", e);
        }
    }
}
