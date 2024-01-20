package org.example.managers;

import org.example.models.NotificationRequest;

public interface INotificationSender {
    void sendNotification(NotificationRequest notificationRequest);
}
