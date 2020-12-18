package com.codegym.service;

import com.codegym.model.Customer;

import java.util.Optional;

public interface IService<T> {
    Iterable<T> findAll();

    T findById(Long id);

    void save(T obj);

    void remove(Long id);
}
