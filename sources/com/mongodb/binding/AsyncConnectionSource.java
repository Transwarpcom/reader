package com.mongodb.binding;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.connection.AsyncConnection;
import com.mongodb.connection.ServerDescription;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncConnectionSource.class */
public interface AsyncConnectionSource extends ReferenceCounted {
    ServerDescription getServerDescription();

    SessionContext getSessionContext();

    void getConnection(SingleResultCallback<AsyncConnection> singleResultCallback);

    @Override // com.mongodb.binding.ReferenceCounted
    AsyncConnectionSource retain();
}
