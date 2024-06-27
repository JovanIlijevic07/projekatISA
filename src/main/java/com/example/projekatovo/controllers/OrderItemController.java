package com.example.projekatovo.controllers;

import com.example.projekatovo.models.OrderItemModel;
import com.example.projekatovo.services.OrderItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order-item")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @GetMapping("get-order-item-list")
    public List<OrderItemModel> getOrderItemList() {
        return orderItemService.getAllOrderItems();
    }

    @PostMapping("create-order-item-body")
    public ResponseEntity<?> createOrderItemBody(@RequestBody @Valid OrderItemModel orderItemModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Neuspesno kreiran stavka narudžbe!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        OrderItemModel createdOrderItem = orderItemService.createOrderItem(orderItemModel);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("get-order-item/{id}")
    public ResponseEntity<OrderItemModel> getOrderItemById(@PathVariable Integer id) {
        return orderItemService.getOrderItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("delete-order-item/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Integer id) {
        orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
