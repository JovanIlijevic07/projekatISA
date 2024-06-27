package com.example.projekatovo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemModel {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private int quantity;
    private double totalPrice;
}
