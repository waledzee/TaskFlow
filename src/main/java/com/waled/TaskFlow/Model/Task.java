package com.waled.TaskFlow.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title" ,nullable = false)
    @JsonProperty("title")
    private String title;
    @Column(name="description" ,nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(name = "due_date")
    @JsonProperty("dueDate")
    private LocalDate dueDate;

    public Task() {}

    public Task(TaskStatus status, LocalDate dueDate, String description, String title, Long id, User user) {
        this.status = status;
        this.dueDate = dueDate;
        this.description = description;
        this.title = title;
        this.id = id;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters and Setters
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    @JsonProperty("status")

    private TaskStatus status = TaskStatus.TODO;



}

