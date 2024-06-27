package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.dto.BrandDto;

import java.util.List;

public interface BrandService {
    BrandDto findById(Integer id);
    List<BrandDto> fetchAll();
}
