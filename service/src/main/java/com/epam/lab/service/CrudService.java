package com.epam.lab.service;

public interface CrudService<T> {
    T create(T element);
    T read(T element);
    T update(T element);
    void delete(T element);
}
