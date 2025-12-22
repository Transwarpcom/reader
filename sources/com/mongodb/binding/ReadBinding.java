package com.mongodb.binding;

import com.mongodb.ReadPreference;
import com.mongodb.session.SessionContext;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ReadBinding.class */
public interface ReadBinding extends ReferenceCounted {
    ReadPreference getReadPreference();

    ConnectionSource getReadConnectionSource();

    SessionContext getSessionContext();

    @Override // com.mongodb.binding.ReferenceCounted
    ReadBinding retain();
}
