package com.Dp.NotesSphere.Service;

import com.Dp.NotesSphere.Enum.TaskStatus;
import com.Dp.NotesSphere.Modals.Task;
import com.Dp.NotesSphere.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public Task createTask(Task task, String userId) {
        task.setUserId(userId);
        task.setTaskStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepo.save(task);
    }

    public List<Task> getAllTasks(String userId) {
        return taskRepo.findByUserId(userId);
    }

    public Optional<Task> getTaskById(String id, String userId) {
        return taskRepo.findById(id)
                .filter(task -> task.getUserId().equals(userId));
    }

    public Task updateTask(String id, Task updatedTask, String userId) {
        Task existing = getTaskById(id, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setDueDate(updatedTask.getDueDate());
        existing.setTaskStatus(updatedTask.getTaskStatus());
        existing.setUpdatedAt(LocalDateTime.now());
        return taskRepo.save(existing);
    }

    public void deleteTask(String id, String userId) {
        Task existing = getTaskById(id, userId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepo.delete(existing);
    }
}
