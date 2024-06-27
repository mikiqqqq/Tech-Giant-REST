package com.spring_boot.webshop_app.mapper;

import com.spring_boot.webshop_app.dto.BrandDto;
import com.spring_boot.webshop_app.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandDtoMapper {

    public BrandDto map(Brand brand) {

        if (brand == null) {
            return null;
        }

        return BrandDto.builder()
                .id(brand.getId())
                .title(brand.getTitle())
                .build();
    }
}
