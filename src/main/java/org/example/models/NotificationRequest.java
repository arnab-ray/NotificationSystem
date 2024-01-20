package org.example.models;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class NotificationRequest {
    private final String userId;
    private final String message;
    private final NotificationMode channel;
    private final Priority priority;
    private final NotificationState state;

    public NotificationRequest(@NonNull String userId, @NonNull String message, @NonNull NotificationMode channel, @NonNull Priority priority) {
        this.userId = userId;
        this.message = message;
        this.channel = channel;
        this.priority = priority;
        this.state = NotificationState.WAITING;
    }

    @Override
    public String toString() {
        return this.userId + ":" + this.message + ":" + this.channel + ":" + this.priority;
    }
}
