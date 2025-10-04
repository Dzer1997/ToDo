package com.example.todo.IntegrationTest;

import com.example.todo.model.Category;
import com.example.todo.model.Task;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Task task;
    private Category category;

    @BeforeEach
    void setUp() {
        // initialiser category
        category = new Category(); // <- vigtig linje
        category.setName("Work");

        // initialiser task
        task = new Task();
        task.setTitle("Test Task");
        task.setDescription("test description");
    }

    @Test
    void getAllTasks_shouldReturnOkAndList() throws Exception {
        // --- arrange ---
        String categoryJson = objectMapper.writeValueAsString(category);

        String response = mockMvc.perform(post("/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(categoryJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        Category savedCategory = objectMapper.readValue(response, Category.class);

        var taskDto = new com.example.todo.dto.TaskDto();
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setStatus(task.getStatus());
        taskDto.setCategoryId(savedCategory.getId());

        String taskJson = objectMapper.writeValueAsString(taskDto);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(taskJson))
                .andExpect(status().isOk());

        // --- act ---
        String getResponse = mockMvc.perform(get("/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // --- assert ---
        List<Task> tasks = objectMapper.readValue(getResponse,
                new TypeReference<List<Task>>() {});
        assertFalse(tasks.isEmpty());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }


    @Test
    void addTask_shouldCreateTaskAndReturn201() throws Exception {
        // arrange

        // act

        // assert
    }
}
