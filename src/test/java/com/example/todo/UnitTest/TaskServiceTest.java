package com.example.todo.UnitTest;

import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private TaskService taskService;

    private Task task;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category();
        category.setId(1L);
        category.setName("Work");

        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("test description");
        task.setCategory(category);
    }

    @Test
    void addTask_shouldSaveTask_whenCategoryExists() {
        // arrange
        when(categoryService.findById(1L)).thenReturn(Optional.of(category));
        when(taskRepository.save(task)).thenReturn(task);
        // act
        Task savedTask = taskService.addTask(task);
        // assert
        assertNotNull(savedTask, "Saved task should not be null");
        assertEquals("Test Task", savedTask.getTitle(), "Title should math");
        assertEquals(category, savedTask.getCategory(), "Category should math");

        verify(categoryService, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void addTask_shouldThrowException_whenCategoryNotFound() {
        // arrange
        when(categoryService.findById(1L)).thenReturn(Optional.empty());

        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("test description");

        Category fakeCategory = new Category();
        fakeCategory.setId(1L);
        task.setCategory(fakeCategory);
        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.addTask(task);
        });

        assertEquals("Category not found", exception.getMessage());

        verify(taskRepository, never()).save(any(Task.class));
        verify(categoryService, times(1)).findById(1L);
    }

    @Test
    void updateTask_shouldUpdateFields_whenTaskExists() {
        // arrange
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(task)).thenReturn(task);

        // act
        Task updatedTask = taskService.updateTask(1L, task);

        // assert
        assertEquals(task.getTitle(), updatedTask.getTitle());
        assertEquals(task.getDescription(), updatedTask.getDescription());
        assertEquals(task.getCategory(), updatedTask.getCategory());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task);
    }


    @Test
    void updateTask_shouldThrowException_whenTaskNotFound() {
        // arrange
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        // act & assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(1L, task);
        });

        assertEquals("Task not found", exception.getMessage());

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, never()).save(task);

    }

    @Test
    void deleteTask_shouldCallRepositoryDelete() {
        // act
        taskService.deleteTask(1L);
        // assert
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
