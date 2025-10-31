package com.learnflix.service;

import com.learnflix.entity.Category;
import com.learnflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category saveCategory(Category category){
        return repository.save(category);
    }

    public void deleteCategory(Long id){
        repository.deleteById(id);
    }

    public Optional<Category> findById(Long id){
        return repository.findById(id);
    }
}
