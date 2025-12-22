package com.mongodb.binding;

import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/WriteBinding.class */
public interface WriteBinding extends ReferenceCounted {
    ConnectionSource getWriteConnectionSource();

    SessionContext getSessionContext();

    @Override // com.mongodb.binding.ReferenceCounted
    WriteBinding retain();
}
