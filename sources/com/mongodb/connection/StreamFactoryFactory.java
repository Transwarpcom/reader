package com.mongodb.connection;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/connection/StreamFactoryFactory.class */
public interface StreamFactoryFactory {
    StreamFactory create(SocketSettings socketSettings, SslSettings sslSettings);
}
