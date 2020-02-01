package com.epam.lab.repository;

import com.epam.lab.specification.EntitySpecification;

import java.util.List;

public interface EntityRepository<T> {

    T create(T entity);
    void update(T entity);
    void delete(T entity);
    long count();

    List<T> query(EntitySpecification specification);
}
