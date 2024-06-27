package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.dto.BrandDto;
import com.spring_boot.webshop_app.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/api/brand")
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping(value = "/fetch-all")
    ResponseEntity<List<BrandDto>> fetchAll() {
        return ResponseEntity
                .ok()
                .body(brandService.fetchAll());
    }

    @GetMapping(value = "/by-id/{id}")
    ResponseEntity<BrandDto> findById(@PathVariable Integer id) {

        return ResponseEntity
                .ok()
                .body(brandService.findById(id));
    }
}
