package com.epam.lab.repository;

import com.epam.lab.specification.EntitySpecification;

import java.util.List;

public interface EntityRepository<T> {

    abstract void add(T entity);
    abstract void update(T entity);
    abstract void remove(T entity);

    abstract List<T> query(EntitySpecification specification);

}
