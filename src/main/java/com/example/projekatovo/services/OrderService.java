package com.example.projekatovo.services;

import com.example.projekatovo.entities.Order;
import com.example.projekatovo.mapper.OrderMapper;
import com.example.projekatovo.models.OrderModel;
import com.example.projekatovo.repositorie.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository orderRepository;

    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toModel)
                .collect(Collectors.toList());
    }

    public OrderModel createOrder(OrderModel orderModel) {
        Order order = OrderMapper.toEntity(orderModel);
        order = orderRepository.save(order);
        return OrderMapper.toModel(order);
    }

    public Optional<OrderModel> getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper::toModel);
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
