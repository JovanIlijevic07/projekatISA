package com.example.projekatovo.controllers;

import com.example.projekatovo.models.CategoryModel;
import com.example.projekatovo.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("get-category-list")
    public List<CategoryModel> getCategoryList() {
        return categoryService.getAllCategories();
    }

    @PostMapping("create-category-body")
    public ResponseEntity<?> createCategoryBody(@RequestBody @Valid CategoryModel categoryModel, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>("Neuspesno kreirana kategorija!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CategoryModel createdCategory = categoryService.createCategory(categoryModel);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("get-category/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("delete-category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
