package com.waled.TaskFlow.Controller;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import com.waled.TaskFlow.Repository.TaskRepository;
import com.waled.TaskFlow.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    TaskRepository taskRepository;
    @GetMapping("api/tasks/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable  TaskStatus status)
    {
        return taskService.getTasksByStatus(status);
    }

}
