package com.example.todo.service;

import com.example.todo.model.Category;
import com.example.todo.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;


    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category addCategory(Category category){
        return repository.save(category);
    }

    public List<Category> getAllCategory(){
        return repository.findAll();
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }


    public Category updateCategory(Long id,Category updatedCategory){
        return repository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    category.setTasks(updatedCategory.getTasks());
                    return repository.save(category);

                })
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id){
        repository.deleteById(id);
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

}
