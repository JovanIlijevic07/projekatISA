package com.example.projekatovo.services;

import com.example.projekatovo.entities.Product;
import com.example.projekatovo.mapper.ProductMapper;
import com.example.projekatovo.models.ProductModel;
import com.example.projekatovo.repositorie.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private IProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toModel)
                .collect(Collectors.toList());
    }

    public ProductModel createProduct(ProductModel productModel) {
        Product product = ProductMapper.toEntity(productModel);
        product = productRepository.save(product);
        return ProductMapper.toModel(product);
    }

    public Optional<ProductModel> getProductById(Integer productId) {
        return productRepository.findById(productId)
                .map(ProductMapper::toModel);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
