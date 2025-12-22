package com.mongodb.internal.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/CommandEventSender.class */
interface CommandEventSender {
    void sendStartedEvent();

    void sendFailedEvent(Throwable th);

    void sendSucceededEvent(ResponseBuffers responseBuffers);

    void sendSucceededEventForOneWayCommand();
}
