package com.waled.TaskFlow.Service;

import com.waled.TaskFlow.Model.Task;
import com.waled.TaskFlow.Repository.TaskRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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

    @Scheduled(cron = "0 0 8 * * *") // كل يوم الساعة 8 صباحًا
    public void sendTaskReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        List<Task> tasksDueSoon = taskRepository.findByDueDateBetween(now, tomorrow);
        for (Task task : tasksDueSoon) {
            emailService.sendTaskReminder(task.getUser().getEmail(), task.getTitle(), task.getDueDate());
        }
    }
}