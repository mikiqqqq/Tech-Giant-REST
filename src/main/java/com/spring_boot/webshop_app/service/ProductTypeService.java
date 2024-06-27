package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.model.ProductType;

import java.util.List;

public interface ProductTypeService {
    List<ProductType> fetchAll();
}
