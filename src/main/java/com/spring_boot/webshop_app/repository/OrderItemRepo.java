package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
    @Query("SELECT oi FROM OrderItem oi JOIN FETCH oi.item WHERE oi.orderId = :orderId")
    List<OrderItem> findAllByOrderId(@Param("orderId") Integer orderId);
    void deleteAllByOrderId(Integer orderId);
    void deleteAllByItemIdAndOrderId(Integer itemId, Integer orderId);
    void deleteById(int id);
    Optional<OrderItem> findByOrderIdAndItemId(Integer orderId, Integer itemId);
    @Modifying
    @Query("UPDATE OrderItem oi SET oi.quantity = :quantity WHERE oi.id = :id")
    void updateOrderItemQuantity(@Param("id") Integer id, @Param("quantity") Integer quantity);
    // Custom method to get the quantity of an OrderItem
    @Query("SELECT oi.quantity FROM OrderItem oi WHERE oi.id = :id")
    Integer getOrderItemQuantity(@Param("id") Integer id);
}
