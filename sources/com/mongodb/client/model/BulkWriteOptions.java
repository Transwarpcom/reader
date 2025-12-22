package com.mongodb.client.model;

import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/BulkWriteOptions.class */
public final class BulkWriteOptions {
    private boolean ordered = true;
    private Boolean bypassDocumentValidation;

    public boolean isOrdered() {
        return this.ordered;
    }

    public BulkWriteOptions ordered(boolean ordered) {
        this.ordered = ordered;
        return this;
    }

    @Nullable
    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public BulkWriteOptions bypassDocumentValidation(@Nullable Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public String toString() {
        return "BulkWriteOptions{ordered=" + this.ordered + ", bypassDocumentValidation=" + this.bypassDocumentValidation + '}';
    }
}
