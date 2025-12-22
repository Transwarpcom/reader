package com.mongodb.binding;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AsyncReadWriteBinding.class */
public interface AsyncReadWriteBinding extends AsyncReadBinding, AsyncWriteBinding, ReferenceCounted {
    @Override // com.mongodb.binding.AsyncReadBinding, com.mongodb.binding.ReferenceCounted
    AsyncReadWriteBinding retain();
}
