package com.example.todo.SpecificationTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.example.todo.UnitTest.CategoryService;
import com.example.todo.UnitTest.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class TaskStepDefinitions {

    @Autowired
    private TaskService taskService;

    @Autowired
    private CategoryService categoryService;

    private Category category;
    private Task task;

    @Given("a category exists")
    public void a_category_exists() {
        category = new Category();
        category.setName("Work");
        categoryService.addCategory(category);
    }

    @Given("no category exists")
    public void no_category_exists() {
        category = null;
    }

    @When("a user creates a new task with a title, description, and deadline")
    public void user_creates_new_task() {
        task = new Task();
        task.setTitle("1");
        task.setDescription("2");
        task.setDeadline(LocalDate.parse("2025-09-15"));
        task.setStatus(false);
        task.setCategory(category);

        taskService.addTask(task);


        if (category != null) {
            category.getTasks().add(task);
        }
    }

    @When("a user tries to create a new task")
    public void user_tries_to_create_task() {
        task = new Task();
        task.setTitle("1");
        task.setDescription("2");
        task.setDeadline(LocalDate.parse("2025-09-15"));
        task.setStatus(false);
        task.setCategory(category);

        try {
            taskService.addTask(task);
        } catch (Exception e) {
            // her kan du gemme fejlen til senere assert
        }
    }

    @Then("the task is successfully created")
    public void task_successfully_created() {
        assertTrue(category.getTasks().contains(task));
        assertEquals("1", task.getTitle());
        assertEquals("2", task.getDescription());
        assertEquals(LocalDate.parse("2025-09-15"), task.getDeadline());
    }

    @Then("an error is returned")
    public void error_is_returned() {
        // her kan du assert at korrekt fejl blev kastet
    }
}
