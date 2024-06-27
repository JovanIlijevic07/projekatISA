package com.example.projekatovo.repositorie;

import com.example.projekatovo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
