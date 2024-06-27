package com.example.projekatovo.mapper;

import com.example.projekatovo.entities.Category;
import com.example.projekatovo.models.CategoryModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryModel toModel(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryModel.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public static Category toEntity(CategoryModel categoryModel) {
        if (categoryModel == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryModel.getId());
        category.setName(categoryModel.getName());
        category.setDescription(categoryModel.getDescription());
        return category;
    }

    public static List<CategoryModel> toModelList(List<Category> categories) {
        return categories.stream()
                .map(CategoryMapper::toModel)
                .collect(Collectors.toList());
    }
}
