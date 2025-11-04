package com.Dp.NotesSphere.Modals;

import com.Dp.NotesSphere.Enum.TaskStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("Task")
public class Task {
    @Id
    private String id;
    private String username;
    private String title;
    private String description;
    private LocalDateTime dueDate;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String userId;

    private TaskStatus taskStatus;

    public Task() {
    }

    public Task(String id, String username, String title, String description, LocalDateTime dueDate, boolean completed, boolean reminderSet,LocalDateTime createdAt,LocalDateTime updatedAt,String userId) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
        this.userId=userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
}
