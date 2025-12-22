package org.springframework.jmx.export.notification;

import javax.management.Notification;

@FunctionalInterface
/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/notification/NotificationPublisher.class */
public interface NotificationPublisher {
    void sendNotification(Notification notification) throws UnableToSendNotificationException;
}
