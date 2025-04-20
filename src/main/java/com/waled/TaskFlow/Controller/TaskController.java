package com.waled.TaskFlow.Controller;

import com.waled.TaskFlow.Repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    TaskRepository taskRepository;
    @GetMapping("api/welcome")
    public List findAllTasks ()
    {
        return "welcome to the first api from task controller";
    }
}
