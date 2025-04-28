package com.waled.TaskFlow.Controller;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import com.waled.TaskFlow.Model.User;
import com.waled.TaskFlow.Service.TaskService;
import com.waled.TaskFlow.Service.TaskServiceimpl;
import com.waled.TaskFlow.Service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;


    public TaskController(TaskService taskService ,UserService userService) {
        this.taskService = taskService;
        this.userService=userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("Task not found with id: " + id));
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/page")
    public ResponseEntity<Map<String, Object>> getAllTasksBYPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Task> pageTasks = taskService.getAllTasksByPage(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("tasks", pageTasks.getContent());
        response.put("currentPage", pageTasks.getNumber());
        response.put("totalItems", pageTasks.getTotalElements());
        response.put("totalPages", pageTasks.getTotalPages());

        return ResponseEntity.ok(response);
    }

@PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication authentication) {
        System.out.println("Username from authentication: " + authentication.getName());
        User user = userService.findByUserName(authentication.getName());
        if (user == null) {
            throw new RuntimeException("User not found: " + authentication.getName());
        }
        task.setUser(user);
        Task createdTask = taskService.createTask(task);
        System.out.println("Saved task with user_id: " + createdTask.getUser().getId());
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id) {
        Task updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable String status) {
        List<Task> tasks = taskService.getTasksByStatus(TaskStatus.valueOf(status));
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/{id}/{status}")
    public ResponseEntity<Task> UpdatedTaskStatus(@PathVariable Long id, @PathVariable String status) {
        Task task = taskService.updateTaskStatus(id, TaskStatus.valueOf(status));
        return ResponseEntity.ok(task);
    }

}