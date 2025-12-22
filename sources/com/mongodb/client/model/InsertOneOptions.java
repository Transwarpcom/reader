package com.mongodb.client.model;

import com.mongodb.lang.Nullable;

/* loaded from: reader.jar:BOOT-INF/lib/mongodb-driver-core-3.8.2.jar:com/mongodb/client/model/InsertOneOptions.class */
public final class InsertOneOptions {
    private Boolean bypassDocumentValidation;

    @Nullable
    public Boolean getBypassDocumentValidation() {
        return this.bypassDocumentValidation;
    }

    public InsertOneOptions bypassDocumentValidation(@Nullable Boolean bypassDocumentValidation) {
        this.bypassDocumentValidation = bypassDocumentValidation;
        return this;
    }

    public String toString() {
        return "InsertOneOptions{bypassDocumentValidation=" + this.bypassDocumentValidation + '}';
    }
}
