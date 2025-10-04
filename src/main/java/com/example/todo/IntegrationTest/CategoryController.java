package com.example.todo.IntegrationTest;

import com.example.todo.dto.CategoryDto;
import com.example.todo.model.Category;
import com.example.todo.UnitTest.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<Category> getAllCategory() {
        return service.getAllCategory();
    }

    @PostMapping
    public Category addCategory(@RequestBody CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return service.addCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = new Category();
        updatedCategory.setName(categoryDto.getName());
        return service.updateCategory(id, updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}
