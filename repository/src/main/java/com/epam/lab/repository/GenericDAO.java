package com.epam.lab.repository;

import java.sql.Connection;

public abstract class GenericDAO {
    // will be injected by transaction manager
    protected Connection connection;
}
