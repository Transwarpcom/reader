package com.mongodb.internal.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/NoOpCommandEventSender.class */
class NoOpCommandEventSender implements CommandEventSender {
    NoOpCommandEventSender() {
    }

    @Override // com.mongodb.internal.connection.CommandEventSender
    public void sendStartedEvent() {
    }

    @Override // com.mongodb.internal.connection.CommandEventSender
    public void sendFailedEvent(Throwable t) {
    }

    @Override // com.mongodb.internal.connection.CommandEventSender
    public void sendSucceededEvent(ResponseBuffers responseBuffers) {
    }

    @Override // com.mongodb.internal.connection.CommandEventSender
    public void sendSucceededEventForOneWayCommand() {
    }
}
