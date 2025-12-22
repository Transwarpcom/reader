package com.mongodb.binding;

import com.mongodb.ReadPreference;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncReadBinding.class */
public interface AsyncReadBinding extends ReferenceCounted {
    ReadPreference getReadPreference();

    SessionContext getSessionContext();

    void getReadConnectionSource(SingleResultCallback<AsyncConnectionSource> singleResultCallback);

    @Override // com.mongodb.binding.ReferenceCounted
    AsyncReadBinding retain();
}
