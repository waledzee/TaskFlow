package com.waled.TaskFlow.Service;


import com.waled.TaskFlow.Exception.TaskNotFoundException;
import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Model.TaskStatus;
import com.waled.TaskFlow.Repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskServiceimpl implements TaskService{
    private final TaskRepository taskRepository;

    public TaskServiceimpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task)
    {
        task.setDueDate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id)
    {
       return taskRepository.findById(id);
    }
    @Override
    public List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }
    @Override
    public Page<Task> getAllTasksByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
    }


    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id)
    {
        Task task = getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);

    }
    @Override
   public List<Task> getTasksByStatus(TaskStatus status)
    {
        return taskRepository.findByStatus(status);
    }

    public Task updateTaskStatus(Long id, TaskStatus newStatus)
    {
        Task task = getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }
    }



