package com.spring_boot.webshop_app.service.impl;

import com.spring_boot.webshop_app.dto.BrandDto;
import com.spring_boot.webshop_app.mapper.BrandDtoMapper;
import com.spring_boot.webshop_app.repository.BrandRepo;
import com.spring_boot.webshop_app.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    final
    BrandRepo brandRepo;

    final
    BrandDtoMapper brandDtoMapper;

    @Autowired
    public BrandServiceImpl(BrandRepo brandRepo, BrandDtoMapper brandDtoMapper) {
        this.brandRepo = brandRepo;
        this.brandDtoMapper = brandDtoMapper;
    }

    @Override
    public List<BrandDto> fetchAll(){
        return brandRepo.findAll()
                .stream()
                .map(brandDtoMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDto findById(Integer id) {
        return brandRepo.findById(id)
                .map(brandDtoMapper::map)
                .orElse(null);
    }
}
