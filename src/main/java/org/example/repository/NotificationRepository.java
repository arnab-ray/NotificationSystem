package org.example.repository;

import org.example.models.NotificationRequest;
import org.example.models.Priority;

import java.util.LinkedList;
import java.util.List;

public class NotificationRepository implements INotificationRepository {

    private final List<NotificationRequest> high;
    private final List<NotificationRequest> medium;
    private final List<NotificationRequest> low;

    public NotificationRepository() {
        this.high = new LinkedList<>();
        this.medium = new LinkedList<>();
        this.low = new LinkedList<>();
    }

    public void addNotificationRequest(NotificationRequest notificationRequest) {
        if (notificationRequest.getPriority() == Priority.HIGH) {
            this.high.add(notificationRequest);
        } else if (notificationRequest.getPriority() == Priority.MEDIUM) {
            this.medium.add(notificationRequest);
        } else {
            this.low.add(notificationRequest);
        }
    }

    public List<NotificationRequest> getHighPriorityMessages() {
        return this.high;
    }

    public List<NotificationRequest> getMediumPriorityMessages() {
        return this.medium;
    }

    public List<NotificationRequest> getLowPriorityMessages() {
        return this.low;
    }

    public NotificationRequest consumeMessage(Priority priority) {
        if (priority == Priority.HIGH) {
            synchronized (this.high) {
                if (!this.high.isEmpty()) {
                    return this.high.remove(0);
                } else {
                    return null;
                }
            }
        } else if (priority == Priority.MEDIUM) {
            synchronized (this.medium) {
                if (!this.medium.isEmpty()) {
                    return this.medium.remove(0);
                } else {
                    return null;
                }
            }
        } else {
            synchronized (this.low) {
                if (!this.low.isEmpty()) {
                    return this.low.remove(0);
                } else {
                    return null;
                }
            }
        }
    }
}
