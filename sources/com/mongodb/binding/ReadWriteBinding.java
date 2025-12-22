package com.mongodb.binding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/ReadWriteBinding.class */
public interface ReadWriteBinding extends ReadBinding, WriteBinding, ReferenceCounted {
    @Override // com.mongodb.binding.ReadBinding, com.mongodb.binding.ReferenceCounted
    ReadWriteBinding retain();
}
