package com.codegym.service;

import com.codegym.model.Province;
import com.codegym.repositories.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void save(Province obj) {
        provinceRepository.save(obj);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }
}
