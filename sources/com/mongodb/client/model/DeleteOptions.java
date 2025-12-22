package com.mongodb.client.model;

import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/DeleteOptions.class */
public class DeleteOptions {
    private Collation collation;

    @Nullable
    public Collation getCollation() {
        return this.collation;
    }

    public DeleteOptions collation(@Nullable Collation collation) {
        this.collation = collation;
        return this;
    }

    public String toString() {
        return "DeleteOptions{collation=" + this.collation + '}';
    }
}
