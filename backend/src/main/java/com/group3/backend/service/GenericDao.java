package com.group3.backend.service;

import java.util.List;

public interface GenericDao<T> {

    List<T> findAll();
    void insert(T t);
    void update(T t);
    void executeUpdate(T t);
    void delete(T t);
}
