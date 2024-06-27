package com.example.projekatovo.repositorie;

import com.example.projekatovo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}
