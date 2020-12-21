package com.codegym.service;

import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProvinceService {
    List<Province> findAll();

    Page<Province> findAll(Pageable pageable) throws Exception;

    List<Province> search(String keyword);

    Page<Province> search(String keyword, Pageable pageable);

    Province findOne(Long id) throws Exception;

    Province save(Province province);

    List<Province> save(List<Province> provinces);

    boolean exists(Long id);

    List<Province> findAll(List<Long> ids);

    long count();

    void delete(Long id);

    void delete(Province obj);

    void delete(List<Province> provinces);

    void deleteAll();
}
