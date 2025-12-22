package com.mongodb.binding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ReferenceCounted.class */
public interface ReferenceCounted {
    int getCount();

    ReferenceCounted retain();

    void release();
}
