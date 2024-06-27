package com.spring_boot.webshop_app.service.impl;

import com.spring_boot.webshop_app.model.ProductType;
import com.spring_boot.webshop_app.repository.ProductTypeRepo;
import com.spring_boot.webshop_app.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    final
    ProductTypeRepo productTypeRepo;

    @Autowired
    public ProductTypeServiceImpl(ProductTypeRepo productTypeRepo) {
        this.productTypeRepo = productTypeRepo;
    }

    @Override
    public List<ProductType> fetchAll(){
        return productTypeRepo.findAll();
    }
}
