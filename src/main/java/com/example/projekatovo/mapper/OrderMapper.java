package com.example.projekatovo.mapper;

import com.example.projekatovo.entities.Order;
import com.example.projekatovo.entities.OrderItem;
import com.example.projekatovo.entities.Product;
import com.example.projekatovo.entities.User;
import com.example.projekatovo.models.OrderModel;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderModel toModel(Order order) {
        if (order == null) {
            return null;
        }

        List<Integer> productIds = order.getOrderItems().stream()
                .map(orderItem -> orderItem.getProduct().getId())
                .collect(Collectors.toList());

        return OrderModel.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .userId(order.getUser().getId())
                .productIds(productIds)
                .totalPrice(order.getTotalPrice())
                .build();
    }

    public static Order toEntity(OrderModel orderModel) {
        if (orderModel == null) {
            return null;
        }
        Order order = new Order();
        order.setId(orderModel.getId());
        order.setOrderDate(orderModel.getOrderDate());

        // Postavi korisnika ako je dostupan
        if (orderModel.getUserId() != null) {
            User user = new User();
            user.setId(orderModel.getUserId());
            order.setUser(user);
        }

        // Postavi proizvode ako su dostupni
        if (orderModel.getProductIds() != null) {
            List<OrderItem> orderItems = orderModel.getProductIds().stream()
                    .map(productId -> {
                        OrderItem orderItem = new OrderItem();
                        Product product = new Product();
                        product.setId(productId);
                        orderItem.setProduct(product);
                        orderItem.setOrder(order);
                        return orderItem;
                    })
                    .collect(Collectors.toList());
            order.setOrderItems(orderItems);
        }

        order.setTotalPrice(orderModel.getTotalPrice());
        return order;
    }

    public static List<OrderModel> toModelList(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toModel)
                .collect(Collectors.toList());
    }
}
