package com.example.projekatovo.repositorie;

import com.example.projekatovo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
