package com.Dp.NotesSphere.Controller;

import com.Dp.NotesSphere.Modals.Task;
import com.Dp.NotesSphere.Service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private String extractUserId(HttpServletRequest request) {
        return request.getHeader("X-User-Id");
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, HttpServletRequest request) {
        String userId = extractUserId(request);
        return ResponseEntity.ok(taskService.createTask(task, userId));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(HttpServletRequest request) {
        String userId = extractUserId(request);
        return ResponseEntity.ok(taskService.getAllTasks(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id, HttpServletRequest request) {
        String userId = extractUserId(request);
        return ResponseEntity.of(taskService.getTaskById(id, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(
            @PathVariable String id,
            @RequestBody Task updatedTask,
            HttpServletRequest request) {
        String userId = extractUserId(request);
        return ResponseEntity.ok(taskService.updateTask(id, updatedTask, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id, HttpServletRequest request) {
        String userId = extractUserId(request);
        taskService.deleteTask(id, userId);
        return ResponseEntity.noContent().build();
    }
}
