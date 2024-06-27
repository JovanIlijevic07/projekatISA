package com.example.projekatovo.mapper;

import com.example.projekatovo.entities.Order;
import com.example.projekatovo.entities.OrderItem;
import com.example.projekatovo.entities.Product;
import com.example.projekatovo.models.OrderItemModel;

import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {

    public static OrderItemModel toModel(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        return OrderItemModel.builder()
                .id(orderItem.getId())
                .orderId(orderItem.getOrder().getId())
                .productId(orderItem.getProduct().getId())
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }

    public static OrderItem toEntity(OrderItemModel orderItemModel) {
        if (orderItemModel == null) {
            return null;
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemModel.getId());

        // Postavi narudžbu ako je dostupna
        if (orderItemModel.getOrderId() != null) {
            Order order = new Order();
            order.setId(orderItemModel.getOrderId());
            orderItem.setOrder(order);
        }

        // Postavi proizvod ako je dostupan
        if (orderItemModel.getProductId() != null) {
            Product product = new Product();
            product.setId(orderItemModel.getProductId());
            orderItem.setProduct(product);
        }

        orderItem.setQuantity(orderItemModel.getQuantity());
        orderItem.setTotalPrice(orderItemModel.getTotalPrice());
        return orderItem;
    }

    public static List<OrderItemModel> toModelList(List<OrderItem> orderItems) {
        return orderItems.stream()
                .map(OrderItemMapper::toModel)
                .collect(Collectors.toList());
    }
}
