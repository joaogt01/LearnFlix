package com.learnflix.controller;

import com.learnflix.controller.request.CategoryRequest;
import com.learnflix.controller.response.CategoryResponse;
import com.learnflix.entity.Category;
import com.learnflix.mapper.CategoryMapper;
import com.learnflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/learnflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryResponse> getAllCategories(){
        List<Category> categories = categoryService.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id){
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CategoryResponse saveCategory(@RequestBody CategoryRequest request){
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
