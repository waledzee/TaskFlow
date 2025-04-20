package com.waled.TaskFlow.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @GetMapping("api/welcome")
    public String welcome ()
    {
        return "welcome to the first api from task controller";
    }
}
