package com.example.ProjetIntegre.controllers;

import com.example.ProjetIntegre.entities.Notification;
import com.example.ProjetIntegre.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @PostMapping("/getNotification/{id}")
    public Notification getNotificationById(@PathVariable Long id){
        return this.notificationService.getNotificationById(id);
    }
}
