package com.example.projekatovo.repositorie;

import com.example.projekatovo.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
