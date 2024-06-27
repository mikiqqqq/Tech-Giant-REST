package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.dto.OrderItemAndAmountDto;
import com.spring_boot.webshop_app.form.OrderItemForm;
import com.spring_boot.webshop_app.mapper.OrderItemFormMapper;
import com.spring_boot.webshop_app.model.OrderItem;
import com.spring_boot.webshop_app.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order-item")
public class OrderItemController {

    final
    OrderItemService orderItemService;

    final
    OrderItemFormMapper orderItemFormMapper;

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderItemFormMapper orderItemFormMapper) {
        this.orderItemService = orderItemService;
        this.orderItemFormMapper = orderItemFormMapper;
    }

    @PostMapping(value="/add")
    ResponseEntity<OrderItem> create(@Valid @RequestBody OrderItemForm orderItemForm) {

        return new ResponseEntity<>(
                orderItemService.save(orderItemFormMapper.map(orderItemForm)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value="/update")
    ResponseEntity<OrderItem> update(@Valid @RequestBody OrderItem orderItem) {
        return new ResponseEntity<>(
                orderItemService.update(orderItem),
                HttpStatus.CREATED
        );
    }

    @PostMapping(value="/add-multiple/{quantity}")
    ResponseEntity<String> createMultiple(@Valid @RequestBody OrderItemForm orderItemForm,
                                          @PathVariable Integer quantity) {

        orderItemService.saveMultiple(orderItemFormMapper.map(orderItemForm), quantity);

        return ResponseEntity
                .ok()
                .body("created");
    }

    @GetMapping(value="/find-all-by-orderId/{orderId}")
    ResponseEntity<List<OrderItem>> findAllByOrderId(@PathVariable Integer orderId) {

        return ResponseEntity
                .ok()
                .body(orderItemService.findAllByOrderId(orderId));
    }

    @GetMapping(value="/get-orderItemAmount/{orderId}")
    ResponseEntity<List<OrderItemAndAmountDto>> getOrderItemAmount(@PathVariable Integer orderId) {

        return ResponseEntity
                .ok()
                .body(orderItemService.findItemsAndAmountInOrder(orderId));
    }

    @DeleteMapping(value="/delete-all-by-itemId/itemId={itemId}&orderId={orderId}")
    ResponseEntity<String> deleteAllByItemId(@PathVariable Integer itemId, @PathVariable Integer orderId) {
        orderItemService.deleteAllByItemIdAndOrderId(itemId, orderId);

        return ResponseEntity
                .ok()
                .body("deleted");
    }

    @DeleteMapping(value="/delete/{id}")
    ResponseEntity<String> delete(@PathVariable int id) {
        orderItemService.delete(id);

        return ResponseEntity
                .ok()
                .body("deleted");
    }

    @DeleteMapping(value="/delete-all-by-orderId/{orderId}")
    ResponseEntity<String> deleteAllByOrderId(@PathVariable Integer orderId) {
        orderItemService.deleteAllByOrderId(orderId);

        return ResponseEntity
                .ok()
                .body("deleted");
    }
}
