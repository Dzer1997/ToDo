package com.example.todo.IntegrationTest;

import com.example.todo.dto.TaskDto;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.example.todo.UnitTest.CategoryService;
import com.example.todo.UnitTest.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        task.setDeadline(taskDto.getDeadline());
        task.setStatus(taskDto.getStatus());
        task.setCategory(category);
        return taskService.addTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Category category = categoryService.getCategoryById(taskDto.getCategoryId());
        Task updatedTask = new Task();
        updatedTask.setTitle(taskDto.getTitle());
        updatedTask.setDescription(taskDto.getDescription());
        updatedTask.setDeadline(taskDto.getDeadline());
        updatedTask.setStatus(taskDto.getStatus());
        updatedTask.setCategory(category);
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
