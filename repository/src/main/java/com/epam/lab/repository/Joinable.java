package com.epam.lab.repository;

public interface Joinable<A, B> {
    void join(A a, B b);
}
