package com.example.todo.IntegrationTest;

import com.example.todo.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category;

    @BeforeEach
    void setUp() {
        // her kan du initialisere et Category-objekt
    }

    @Test
    void getAllCategories_shouldReturnOkAndList() throws Exception {
        // arrange

        // act

        // assert
    }

    @Test
    void addCategory_shouldCreateCategoryAndReturn201() throws Exception {
        // arrange

        // act

        // assert
    }
}
