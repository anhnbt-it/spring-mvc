package com.codegym.service;

import com.codegym.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CustomerService extends IService<Customer> {

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByNameContaining(String name, Pageable pageable);
}
