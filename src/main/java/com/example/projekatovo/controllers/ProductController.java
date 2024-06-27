package com.example.projekatovo.controllers;

import com.example.projekatovo.models.ProductModel;
import com.example.projekatovo.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get-product-list")
    public List<ProductModel> getProductList() {
        return productService.getAllProducts();
    }

    @PostMapping("/create-product-body")
    public ResponseEntity<?> createProductBody(@RequestBody @Valid ProductModel productModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Neuspesno kreiran!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ProductModel createdProduct = productService.createProduct(productModel);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
