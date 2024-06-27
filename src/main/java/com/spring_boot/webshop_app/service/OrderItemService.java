package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.dto.OrderItemAndAmountDto;
import com.spring_boot.webshop_app.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem save (OrderItem orderItem);
    OrderItem update(OrderItem orderItem);
    void saveMultiple (OrderItem orderItem, Integer quantity);
    List<OrderItem> findAllByOrderId(Integer orderId);
    List<OrderItemAndAmountDto> findItemsAndAmountInOrder(Integer orderId);
    void deleteAllByItemIdAndOrderId(Integer itemId, Integer orderId);
    void delete(int id);
    void deleteAllByOrderId(Integer orderId);
}
