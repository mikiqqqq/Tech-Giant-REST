package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.model.ProductType;
import com.spring_boot.webshop_app.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api/productType")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping(value = "/fetch-all")
    ResponseEntity<List<ProductType>> fetchAll() {
        return ResponseEntity
                .ok()
                .body(productTypeService.fetchAll());
    }
}
