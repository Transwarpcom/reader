package com.mongodb.connection;

import com.mongodb.ServerAddress;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/StreamFactory.class */
public interface StreamFactory {
    Stream create(ServerAddress serverAddress);
}
