package com.mongodb.binding;

import com.mongodb.connection.Connection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ConnectionSource.class */
public interface ConnectionSource extends ReferenceCounted {
    ServerDescription getServerDescription();

    SessionContext getSessionContext();

    Connection getConnection();

    @Override // com.mongodb.binding.ReferenceCounted
    ConnectionSource retain();
}
