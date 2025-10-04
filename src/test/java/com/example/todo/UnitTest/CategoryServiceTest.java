package com.example.todo.UnitTest;

import com.example.todo.model.Category;
import com.example.todo.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // her kan du initialisere category objektet
    }

    @Test
    void addCategory_shouldSaveCategory() {
        // arrange

        // act

        // assert
    }

    @Test
    void updateCategory_shouldUpdateFields_whenCategoryExists() {
        // arrange

        // act

        // assert
    }

    @Test
    void updateCategory_shouldThrowException_whenCategoryNotFound() {
        // arrange

        // act & assert
    }

    @Test
    void deleteCategory_shouldCallRepositoryDelete() {
        // arrange

        // act

        // assert
    }

    @Test
    void findById_shouldReturnCategory_whenExists() {
        // arrange

        // act

        // assert
    }
}
