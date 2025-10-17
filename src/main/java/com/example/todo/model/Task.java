package com.example.todo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String title;
    @Size(max = 255)
    private String description;
    @FutureOrPresent
    private LocalDate  deadline;
    private String status;
    @ManyToOne
    @NotNull
    @JsonBackReference
    private Category category;

    public Task() {
    }

    public Task(long id, String title, String description, LocalDate  deadline, String status, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public LocalDate  getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate  deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", status=" + status +
                ", category=" + category +
                '}';
    }
}
