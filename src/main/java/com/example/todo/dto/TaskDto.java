package com.example.todo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TaskDto {
    private Long id;

    @NotBlank
    private String title;
    private String description;


    private String deadline;

    private String status;

    @NotNull
    private Long categoryId; // kun id, ikke hele Category

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, String  deadline, String status, Long categoryId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String  getDeadline() {
        return deadline;
    }

    public void setDeadline(String  deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
