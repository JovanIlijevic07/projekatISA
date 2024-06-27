package com.example.projekatovo.mapper;

import com.example.projekatovo.entities.Category;
import com.example.projekatovo.entities.Product;
import com.example.projekatovo.models.ProductModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductModel toModel(Product product) {
        if (product == null) {
            return null;
        }
        return ProductModel.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public static Product toEntity(ProductModel productModel) {
        if (productModel == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        product.setDescription(productModel.getDescription());
        product.setPrice(productModel.getPrice());

        // Postavi kategoriju ako je dostupna
        if (productModel.getCategoryId() != null) {
            Category category = new Category();
            category.setId(productModel.getCategoryId());
            product.setCategory(category);
        }

        return product;
    }

    public static List<ProductModel> toModelList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }
}
