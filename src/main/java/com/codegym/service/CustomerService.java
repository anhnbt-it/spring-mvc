package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CustomerService {

    Page<Customer> findAllByNameContaining(String keyword, Pageable pageable);

    Iterable<Customer> findAll();

    Page<Customer> findAll(Pageable pageable) throws Exception;

    List<Customer> search(String keyword);

    Page<Customer> search(String keyword, Pageable pageable);

    Customer findOne(Long id) throws Exception;

    Customer save(Customer customer) throws DuplicateEmailException;

    Iterable<Customer> save(List<Customer> customers);

    boolean exists(Long id);

    Iterable<Customer> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Customer obj);

    void delete(List<Customer> customers);

    void deleteAll();
}
