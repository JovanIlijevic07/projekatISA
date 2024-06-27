package com.example.projekatovo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductModel {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Integer categoryId;
}
