package com.example.projekatovo.services;

import com.example.projekatovo.entities.OrderItem;
import com.example.projekatovo.mapper.OrderItemMapper;
import com.example.projekatovo.models.OrderItemModel;
import com.example.projekatovo.repositorie.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private IOrderItemRepository orderItemRepository;

    public List<OrderItemModel> getAllOrderItems() {
        return orderItemRepository.findAll().stream()
                .map(OrderItemMapper::toModel)
                .collect(Collectors.toList());
    }

    public OrderItemModel createOrderItem(OrderItemModel orderItemModel) {
        OrderItem orderItem = OrderItemMapper.toEntity(orderItemModel);
        orderItem = orderItemRepository.save(orderItem);
        return OrderItemMapper.toModel(orderItem);
    }

    public Optional<OrderItemModel> getOrderItemById(Integer orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .map(OrderItemMapper::toModel);
    }

    public void deleteOrderItem(Integer orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
