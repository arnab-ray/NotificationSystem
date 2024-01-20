package org.example.managers;

import lombok.NonNull;
import org.example.exceptions.BandwidthException;
import org.example.models.NotificationRequest;

public class NotificationManager implements Runnable {

    private final NotificationRequest notificationRequest;
    private final INotificationSender notificationSender;

    public NotificationManager(@NonNull NotificationRequest notificationRequest, @NonNull INotificationSender notificationSender) {
        this.notificationRequest = notificationRequest;
        this.notificationSender = notificationSender;
    }


    @Override
    public void run() {
        try {
            notificationSender.sendNotification(notificationRequest);
        } catch (BandwidthException e) {
            System.out.println("Exception while sending notification" + e.getMessage());
        }
    }
}
