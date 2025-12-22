package com.mongodb.internal.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/internal/connection/ChangeListener.class */
interface ChangeListener<T> {
    void stateChanged(ChangeEvent<T> changeEvent);
}
