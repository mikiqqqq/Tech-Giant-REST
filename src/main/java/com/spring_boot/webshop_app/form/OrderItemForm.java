package com.spring_boot.webshop_app.form;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItemForm {

    private Integer id;

    @NotNull(message = "Order quantity is required.")
    private Integer quantity;

    @NotNull(message = "Order Id is required.")
    private Integer orderId;

    @NotNull(message = "Item Id is required.")
    private Integer itemId;
}
