package org.springframework.jmx.export.notification;

import org.springframework.jmx.JmxException;

/* loaded from: reader.jar:BOOT-INF/lib/spring-context-5.1.8.RELEASE.jar:org/springframework/jmx/export/notification/UnableToSendNotificationException.class */
public class UnableToSendNotificationException extends JmxException {
    public UnableToSendNotificationException(String msg) {
        super(msg);
    }

    public UnableToSendNotificationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
