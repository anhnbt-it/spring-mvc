package com.codegym.service;

import com.codegym.model.Customer;

import java.util.Optional;

public interface CustomerService {
    Iterable<Customer> findAll();

    Optional<Customer> findById(Long id);

    void save(Customer customer);

    void remove(Long id);
}
