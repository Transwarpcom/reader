package com.mongodb.binding;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncWriteBinding.class */
public interface AsyncWriteBinding extends ReferenceCounted {
    void getWriteConnectionSource(SingleResultCallback<AsyncConnectionSource> singleResultCallback);

    SessionContext getSessionContext();

    @Override // com.mongodb.binding.ReferenceCounted
    AsyncWriteBinding retain();
}
