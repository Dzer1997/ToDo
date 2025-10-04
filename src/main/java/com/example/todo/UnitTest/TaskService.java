package com.example.todo.UnitTest;
import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository TaskRepo;
    private final CategoryService categoryService;

    public TaskService(TaskRepository repository, CategoryService categoryService){
        this.TaskRepo = repository;
        this.categoryService = categoryService;
    }

    public Task addTask(Task task){
        if (task.getCategory() != null) {
            task.getCategory().getTasks().add(task);
        }
        return TaskRepo.save(task);
    }

    public List<Task> getAllTasks(){
        return TaskRepo.findAll();
    }

    public Task updateTask(Long id, Task updatedTask){
        return TaskRepo.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setCategory(updatedTask.getCategory()); // Category allerede sat
                    task.setDeadline(updatedTask.getDeadline());
                    task.setStatus(updatedTask.getStatus());
                    return TaskRepo.save(task);
                })
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id){
        TaskRepo.deleteById(id);
    }

}
