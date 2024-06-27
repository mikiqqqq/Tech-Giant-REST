package com.spring_boot.webshop_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BrandDto {
    private Integer id;
    private String title;
}
