package com.waled.TaskFlow.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender)
    {
        this.mailSender=mailSender;

    }

    public void sendTaskReminder(String to, String taskTitle, LocalDateTime dueDate) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Task Reminder: " + taskTitle);
        message.setText("Dear User,\n\nYour task \"" + taskTitle + "\" is due on " + dueDate +  ". Please complete it on time!\n\nRegards,\nTaskFlow Team");
        mailSender.send(message);
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject("Task Reminder: " + taskTitle);
//        message.setText("Dear User,\n\nYour task \"" + taskTitle + "\" is due on " + dueDate + ". Please complete it on time!\n\nRegards,\nTaskFlow Team");
//        mailSender.send(message);
    }
}

