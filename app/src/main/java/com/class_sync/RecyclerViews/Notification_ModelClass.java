package com.class_sync.RecyclerViews;

public class Notification_ModelClass {
    String NotificationName,NotificationDescription;

    public Notification_ModelClass() {
    }

    public String getNotificationName() {
        return NotificationName;
    }

    public void setNotificationName(String notificationName) {
        NotificationName = notificationName;
    }

    public String getNotificationDescription() {
        return NotificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        NotificationDescription = notificationDescription;
    }

    public Notification_ModelClass(String notificationName, String notificationDescription) {
        NotificationName = notificationName;
        NotificationDescription = notificationDescription;
    }
}
