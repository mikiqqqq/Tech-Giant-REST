package com.spring_boot.webshop_app.dto;
import com.spring_boot.webshop_app.model.Brand;
import com.spring_boot.webshop_app.model.ProductType;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ItemDto {
    private Integer id;
    private String image;
    private String title;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Brand brand; // Included Brand object
    private ProductType productType; // Included ProductType object
    private Integer productionYear;
}
