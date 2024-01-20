package org.example.managers;

import lombok.NonNull;
import org.example.exceptions.BandwidthException;
import org.example.models.NotificationRequest;
import org.example.models.Priority;
import org.example.repository.INotificationRepository;

import java.util.concurrent.Semaphore;

public class NotificationSender implements INotificationSender {
    private final Semaphore semaphore;
    private final INotificationRepository notificationRepository;

    public NotificationSender(@NonNull INotificationRepository notificationRepository) {
        this.semaphore = new Semaphore(3);
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(@NonNull NotificationRequest notificationRequest) {
        notificationRepository.addNotificationRequest(notificationRequest);

        try {
            semaphore.acquire();
            notifyWorld();
            semaphore.release();
        } catch (InterruptedException e) {
            throw new BandwidthException(e.getMessage());
        }
    }

    private void notifyWorld() {
        if (!notificationRepository.getHighPriorityMessages().isEmpty()) {
            consumeAndNotify(Priority.HIGH);
        } else if (!notificationRepository.getMediumPriorityMessages().isEmpty()) {
            consumeAndNotify(Priority.MEDIUM);
        } else if (!notificationRepository.getLowPriorityMessages().isEmpty()) {
            consumeAndNotify(Priority.LOW);
        }
    }

    private void consumeAndNotify(Priority priority) {
        NotificationRequest notificationRequest = notificationRepository.consumeMessage(priority);
        if (notificationRequest != null) {
            System.out.println("Sent notification!! " + notificationRequest);
        }
    }
}
