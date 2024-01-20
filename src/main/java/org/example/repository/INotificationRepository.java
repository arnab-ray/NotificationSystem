package org.example.repository;

import org.example.models.NotificationRequest;
import org.example.models.Priority;

import java.util.List;

public interface INotificationRepository {
    void addNotificationRequest(NotificationRequest notificationRequest);

    List<NotificationRequest> getHighPriorityMessages();

    List<NotificationRequest> getMediumPriorityMessages();

    List<NotificationRequest> getLowPriorityMessages();

    NotificationRequest consumeMessage(Priority priority);
}
