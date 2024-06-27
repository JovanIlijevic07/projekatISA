package com.example.projekatovo.services;

import com.example.projekatovo.entities.Category;
import com.example.projekatovo.mapper.CategoryMapper;
import com.example.projekatovo.models.CategoryModel;
import com.example.projekatovo.repositorie.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::toModel)
                .collect(Collectors.toList());
    }

    public CategoryModel createCategory(CategoryModel categoryModel) {
        Category category = CategoryMapper.toEntity(categoryModel);
        category = categoryRepository.save(category);
        return CategoryMapper.toModel(category);
    }

    public Optional<CategoryModel> getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryMapper::toModel);
    }

    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
