package com.spring_boot.webshop_app.mapper;

import com.spring_boot.webshop_app.form.OrderItemForm;
import com.spring_boot.webshop_app.model.Item;
import com.spring_boot.webshop_app.model.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemFormMapper {

    public OrderItem map(OrderItemForm orderItemForm) {
        if (orderItemForm == null) {
            return null;
        }

        return OrderItem.builder()
                .quantity(orderItemForm.getQuantity())
                .orderId(orderItemForm.getOrderId())
                .item(Item.builder().id(orderItemForm.getItemId()).build()) // Adjusted to set Item
                .build();
    }
}