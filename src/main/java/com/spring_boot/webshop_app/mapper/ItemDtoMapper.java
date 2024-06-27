package com.spring_boot.webshop_app.mapper;

import com.spring_boot.webshop_app.dto.ItemDto;
import com.spring_boot.webshop_app.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoMapper {

    public ItemDto map(Item item) {
        if (item == null) {
            return null;
        }

        return ItemDto.builder()
                .id(item.getId())
                .image(item.getImage())
                .title(item.getTitle())
                .description(item.getDescription())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .brand(item.getBrand()) // Map Brand object
                .productType(item.getProductType()) // Map ProductType object
                .productionYear(item.getProductionYear())
                .build();
    }
}