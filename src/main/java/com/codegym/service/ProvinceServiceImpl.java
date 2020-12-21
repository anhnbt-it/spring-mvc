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
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Page<Province> findAll(Pageable pageable) throws Exception {
        return provinceRepository.findAll(pageable);
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
        return provinceRepository.findOne(id);
    }

    @Override
    public Province save(Province province) {
        return provinceRepository.save(province);
    }

    @Override
    public Iterable<Province> save(List<Province> provinces) {
        return provinceRepository.save(provinces);
    }

    @Override
    public boolean exists(Long id) {
        return provinceRepository.exists(id);
    }

    @Override
    public Iterable<Province> findAll(List<Long> ids) {
        return provinceRepository.findAll(ids);
    }

    @Override
    public long count() {
        return provinceRepository.count();
    }

    @Override
    public void delete(Long id) {
        provinceRepository.delete(id);
    }

    @Override
    public void delete(Province province) {
        provinceRepository.delete(province);
    }

    @Override
    public void delete(List<Province> provinces) {
        provinceRepository.delete(provinces);
    }

    @Override
    public void deleteAll() {
        provinceRepository.deleteAll();
    }
}
