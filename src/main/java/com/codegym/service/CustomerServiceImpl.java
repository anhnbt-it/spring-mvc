package com.codegym.service;

import com.codegym.model.Customer;
import com.codegym.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findAllByNameContaining(String keyword, Pageable pageable) {
        return customerRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) throws Exception {
//        if (true) throw new Exception("a dummy exception");
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> search(String keyword) {
        return null;
    }

    @Override
    public Page<Customer> search(String keyword, Pageable pageable) {
        return customerRepository.findAllByNameContaining(keyword, pageable);
    }

    @Override
    public Customer findOne(Long id) throws Exception {
        Customer target = customerRepository.findOne(id);
        if (target == null) {
            throw new Exception("customer not found!");
        }
        return target;
    }

    @Override
    public Customer save(Customer customer) throws DuplicateEmailException {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
    }

    @Override
    public Iterable<Customer> save(List<Customer> customers) {
        return customerRepository.save(customers);
    }

    @Override
    public boolean exists(Long id) {
        return customerRepository.exists(id);
    }

    @Override
    public Iterable<Customer> findAll(List<Long> ids) {
        return customerRepository.findAll(ids);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void delete(List<Customer> customers) {
        customerRepository.delete(customers);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }
}
