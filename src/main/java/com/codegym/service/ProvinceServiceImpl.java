package com.codegym.service;

import com.codegym.model.Province;
import com.codegym.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public List<Province> findAll() {
        return null;
    }

    @Override
    public Page<Province> findAll(Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public List<Province> search(String keyword) {
        return null;
    }

    @Override
    public Page<Province> search(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public Province findOne(Long id) throws Exception {
        return null;
    }

    @Override
    public Province save(Province province) {
        return null;
    }

    @Override
    public List<Province> save(List<Province> provinces) {
        return null;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    @Override
    public List<Province> findAll(List<Long> ids) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void delete(Province obj) {

    }

    @Override
    public void delete(List<Province> provinces) {

    }

    @Override
    public void deleteAll() {

    }
}
