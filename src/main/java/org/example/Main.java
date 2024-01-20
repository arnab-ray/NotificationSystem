package org.example;

import org.example.managers.INotificationSender;
import org.example.managers.NotificationManager;
import org.example.managers.NotificationSender;
import org.example.models.NotificationMode;
import org.example.models.NotificationRequest;
import org.example.models.Priority;
import org.example.repository.INotificationRepository;
import org.example.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<NotificationRequest> requests = new ArrayList<>();
        requests.add(new NotificationRequest("a", "hi!", NotificationMode.EMAIL, Priority.LOW));
        requests.add(new NotificationRequest("b", "hi!", NotificationMode.EMAIL, Priority.HIGH));
        requests.add(new NotificationRequest("c", "hi!", NotificationMode.EMAIL, Priority.MEDIUM));

        INotificationRepository notificationRepository = new NotificationRepository();
        INotificationSender notificationSender = new NotificationSender(notificationRepository);

        for (int i = 0; i < 35; i++) {
            if (i % 3 == 0)
                requests.add(new NotificationRequest("a->" + i, "hi!", NotificationMode.SMS, Priority.HIGH));
            if (i % 3 == 1)
                requests.add(new NotificationRequest("a->" + i, "hi!", NotificationMode.WHATSAPP, Priority.MEDIUM));
            if (i % 3 == 2)
                requests.add(new NotificationRequest("a->" + i, "hi!", NotificationMode.EMAIL, Priority.LOW));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(15);

//        executorService.execute(new NotificationManager(requests.get(0), notificationSender));
//        executorService.execute(new NotificationManager(requests.get(1), notificationSender));
//        executorService.execute(new NotificationManager(requests.get(2), notificationSender));

        for (int i = 0; i < 38; i++) {
            executorService.execute(new NotificationManager(requests.get(i), notificationSender));
        }
        executorService.shutdown();
    }
}