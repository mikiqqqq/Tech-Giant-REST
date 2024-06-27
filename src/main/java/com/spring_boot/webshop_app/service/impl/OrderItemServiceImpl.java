package com.spring_boot.webshop_app.service.impl;

import com.spring_boot.webshop_app.dto.OrderItemAndAmountDto;
import com.spring_boot.webshop_app.mapper.OrderItemAndAmountDtoMapper;
import com.spring_boot.webshop_app.model.OrderItem;
import com.spring_boot.webshop_app.repository.OrderItemRepo;
import com.spring_boot.webshop_app.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepo orderItemRepo;
    private final OrderItemAndAmountDtoMapper orderItemAndAmountDtoMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepo orderItemRepo, OrderItemAndAmountDtoMapper orderItemAndAmountDtoMapper) {
        this.orderItemRepo = orderItemRepo;
        this.orderItemAndAmountDtoMapper = orderItemAndAmountDtoMapper;
    }

    @Override
    @Transactional
    public OrderItem save(OrderItem orderItem) {
        Optional<OrderItem> existsInDatabase = orderItemRepo.findByOrderIdAndItemId(orderItem.getOrderId(), orderItem.getItem().getId());
        if (existsInDatabase.isPresent()) {
            System.out.println(existsInDatabase.get());
            int updatedQuantity = orderItemRepo.getOrderItemQuantity(existsInDatabase.get().getId()) + orderItem.getQuantity();
            orderItemRepo.updateOrderItemQuantity(existsInDatabase.get().getId(), updatedQuantity);
            return orderItemRepo.findById(existsInDatabase.get().getId()).orElse(null);
        } else {
            return orderItemRepo.save(orderItem);
        }
    }

    @Override
    @Transactional
    public OrderItem update(OrderItem orderItem) {
        orderItemRepo.updateOrderItemQuantity(orderItem.getId(), orderItem.getQuantity());
        return orderItemRepo.findById(orderItem.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void saveMultiple(OrderItem orderItem, Integer quantity) {
        List<OrderItem> orderItemList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            orderItemList.add(OrderItem.builder()
                    .item(orderItem.getItem()) // Adjusted to set Item
                    .orderId(orderItem.getOrderId())
                    .build());
        }
        orderItemRepo.saveAll(orderItemList);
    }

    @Override
    public List<OrderItemAndAmountDto> findItemsAndAmountInOrder(Integer orderId) {
        Map<Integer, Long> map = orderItemRepo.findAllByOrderId(orderId)
                .stream()
                .map(orderItem -> orderItem.getItem().getId()) // Adjusted to get Item ID
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return map.entrySet()
                .stream()
                .map(item -> orderItemAndAmountDtoMapper.map(item.getKey(), item.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItem> findAllByOrderId(Integer orderId) {
        return orderItemRepo.findAllByOrderId(orderId);
    }

    @Override
    @Transactional
    public void deleteAllByItemIdAndOrderId(Integer itemId, Integer orderId) {
        orderItemRepo.deleteAllByItemIdAndOrderId(itemId, orderId);
    }

    @Override
    @Transactional
    public void delete(int id) {
        orderItemRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllByOrderId(Integer orderId) {
        orderItemRepo.deleteAllByOrderId(orderId);
    }
}