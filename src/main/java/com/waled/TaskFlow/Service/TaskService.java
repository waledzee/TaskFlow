package com.waled.TaskFlow.Service;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface TaskService {
    Task createTask(Task task);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
    Task updateTask(Long id, Task taskDetails);
    void deleteTask(Long id);
   List<Task> getTasksByStatus(TaskStatus status);
    Task updateTaskStatus(Long id, TaskStatus newStatus);
}
