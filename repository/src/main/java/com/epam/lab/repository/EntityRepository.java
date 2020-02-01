package com.epam.lab.repository;

import com.epam.lab.specification.EntitySpecification;

import java.util.List;

public interface EntityRepository<T> {

    T save(T entity);
    void update(T entity);
    void remove(T entity);

    List<T> query(EntitySpecification specification);

}
