package com.spring_boot.webshop_app.service.impl;

import com.spring_boot.webshop_app.enumeration.OrderStatus;
import com.spring_boot.webshop_app.model.Order;
import com.spring_boot.webshop_app.repository.OrderRepo;
import com.spring_boot.webshop_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    final
    OrderRepo orderRepo;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public Order fetchOrderById (Integer id){
        return orderRepo.findById(id).orElse(null);
    }

    @Override
    public List<Order> fetchActiveOrders (String email){
        return orderRepo.findOrderByStatusAndEmail(OrderStatus.IN_PROGRESS.toString(), email);
    }

    @Override
    public List<Order> fetchCompletedOrders (String email){
        return orderRepo.findOrderByStatusAndEmail(OrderStatus.COMPLETED.toString(), email);
    }

    @Override
    @Transactional
    public Integer save(Order order){
        orderRepo.save(order);
        return order.getId();
    }

    @Override
    @Transactional
    public void update(Integer orderId, Order updatedOrder) {
        updatedOrder.setId(orderId);
        orderRepo.save(updatedOrder);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        orderRepo.deleteById(id);
    }
}
