package com.spring_boot.webshop_app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItemAndAmountDto {
    private Integer id;
    private Long quantity;
}
