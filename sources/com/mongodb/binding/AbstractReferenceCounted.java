package com.mongodb.binding;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/binding/AbstractReferenceCounted.class */
abstract class AbstractReferenceCounted implements ReferenceCounted {
    private final AtomicInteger referenceCount = new AtomicInteger(1);

    AbstractReferenceCounted() {
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public int getCount() {
        return this.referenceCount.get();
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public ReferenceCounted retain() {
        if (this.referenceCount.incrementAndGet() == 1) {
            throw new IllegalStateException("Attempted to increment the reference count when it is already 0");
        }
        return this;
    }

    @Override // com.mongodb.binding.ReferenceCounted
    public void release() {
        if (this.referenceCount.decrementAndGet() < 0) {
            throw new IllegalStateException("Attempted to decrement the reference count below 0");
        }
    }
}
