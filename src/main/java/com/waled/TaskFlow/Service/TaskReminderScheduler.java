package com.waled.TaskFlow.Service;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TaskReminderScheduler {
    private final TaskRepository taskRepository;
    private final EmailService emailService;

    public TaskReminderScheduler(TaskRepository taskRepository, EmailService emailService) {
        this.taskRepository = taskRepository;
        this.emailService = emailService;
    }

    //@Scheduled(fixedRate = 60000) // كل يوم الساعة 8 صباحًا
    public void sendTaskReminders() {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plusDays(1);
        System.out.println("Scheduler running at: " + now);
        System.out.println("Looking for tasks between: " + now + " and " + tomorrow);
        List<Task> tasksDueSoon = taskRepository.findTasksDueBetween(now, tomorrow);
        System.out.println("Found " + tasksDueSoon.size() + " tasks due soon");
        for (Task task : tasksDueSoon) {
            try {
                if (task.getUser() != null && task.getUser().getEmail() != null) {
                    System.out.println("Sending reminder for task: " + task.getTitle() + " to " + task.getUser().getEmail());
                    emailService.sendTaskReminder(task.getUser().getEmail(), task.getTitle(), task.getDueDate());
                } else {
                    System.out.println("Task " + task.getTitle() + " has no user or email");
                }
            } catch (Exception e) {
                System.out.println("Error sending reminder for task: " + task.getTitle() + ", Error: " + e.getMessage());
            }
        }
    }
}