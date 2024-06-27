package com.example.projekatovo.models;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderModel {
    private Integer id;
    private Date orderDate;
    private Integer userId;
    private List<Integer> productIds;
    private double totalPrice;
}
