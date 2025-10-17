package com.example.todo.controller;

import com.example.todo.dto.TaskDto;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.example.todo.service.CategoryService;
import com.example.todo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final CategoryService categoryService;

    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Task> getAllTask() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task addTask(@Valid @RequestBody TaskDto taskDto) {
        Category category = categoryService.getCategoryById(taskDto.getCategoryId());
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());

        // Konverter deadline fra String til LocalDate
        task.setDeadline(taskDto.getDeadline() != null ? LocalDate.parse(taskDto.getDeadline()) : null);

        task.setStatus(taskDto.getStatus());
        task.setCategory(category);
        return taskService.addTask(task);
    }



    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task existingTask = taskService.getTaskById(id);
        Category category = categoryService.getCategoryById(taskDto.getCategoryId());

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setDeadline(taskDto.getDeadline() != null ? LocalDate.parse(taskDto.getDeadline()) : null);
        existingTask.setStatus(taskDto.getStatus());
        existingTask.setCategory(category);

        return taskService.updateTask(id, existingTask);
    }



    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
