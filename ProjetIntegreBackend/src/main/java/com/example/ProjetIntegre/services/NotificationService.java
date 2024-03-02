package com.example.ProjetIntegre.services;

import com.example.ProjetIntegre.entities.Notification;
import com.example.ProjetIntegre.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public Notification getNotificationById(Long id) {
        return this.notificationRepository.findNotificationById(id);
    }
}
