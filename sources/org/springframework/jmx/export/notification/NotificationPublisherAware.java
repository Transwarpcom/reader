package org.springframework.jmx.export.notification;

import org.springframework.beans.factory.Aware;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/notification/NotificationPublisherAware.class */
public interface NotificationPublisherAware extends Aware {
    void setNotificationPublisher(NotificationPublisher notificationPublisher);
}
