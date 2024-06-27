package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.form.OrderForm;
import com.spring_boot.webshop_app.mapper.OrderFormMapper;
import com.spring_boot.webshop_app.model.Order;
import com.spring_boot.webshop_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    final
    OrderService orderService;

    final
    OrderFormMapper orderFormMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderFormMapper orderFormMapper) {
        this.orderService = orderService;
        this.orderFormMapper = orderFormMapper;
    }

    @GetMapping(value = "/active/{email}")
    ResponseEntity<List<Order>> fetchActiveOrders(@PathVariable String email) {

        return ResponseEntity
                .ok()
                .body(orderService.fetchActiveOrders(email));
    }

    @GetMapping(value = "/completed/{email}")
    ResponseEntity<List<Order>> fetchCompletedOrders(@PathVariable String email) {

        return ResponseEntity
                .ok()
                .body(orderService.fetchCompletedOrders(email));
    }

    @GetMapping(value = "/fetch-order/{id}")
    ResponseEntity<Order> fetchOrderById(@PathVariable Integer id) {

        return ResponseEntity
                .ok()
                .body(orderService.fetchOrderById(id));
    }

    @PostMapping(value="/create")
    ResponseEntity<Integer> create(@Valid @RequestBody OrderForm orderForm) {

        return new ResponseEntity<>(
                orderService.save(orderFormMapper.map(orderForm)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value="/update")
    ResponseEntity<String> update(@Valid @RequestBody OrderForm orderForm) {

        Order updatedOrder = orderFormMapper.map(orderForm);
        orderService.update(updatedOrder.getId(), updatedOrder);

        return ResponseEntity
                .ok()
                .body("updated");
    }

    @DeleteMapping(value="delete/id={id}")
    ResponseEntity<String> delete(@PathVariable Integer id) {
        orderService.delete(id);

        return ResponseEntity
                .ok()
                .body("deleted");
    }   
}
