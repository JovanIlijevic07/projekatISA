package com.example.projekatovo.controllers;

import com.example.projekatovo.models.OrderModel;
import com.example.projekatovo.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/get-order-list")
    public List<OrderModel> getOrderList() {
        return orderService.getAllOrders();
    }

    @PostMapping("/create-order-body")
    public ResponseEntity<?> createOrderBody(@RequestBody @Valid OrderModel orderModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Neuspesno kreiran!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        OrderModel createdOrder = orderService.createOrder(orderModel);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<OrderModel> getOrderById(@PathVariable Integer id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete-order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
